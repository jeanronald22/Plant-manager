package com.dev.plant_management.service;

import com.dev.plant_management.entity.WateringNeed;
import com.dev.plant_management.repository.WateringNeedRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WateringScheduler {
    private final WateringNeedRepository wateringNeedRepository;
    private final EmailService emailService;

    public WateringScheduler(WateringNeedRepository wateringNeedRepository,
                             EmailService emailService) {
        this.wateringNeedRepository = wateringNeedRepository;
        this.emailService = emailService;
    }
    // Vérifie toutes les 10 minutes
    @Scheduled(fixedRate = 1 * 60 * 1000)
    @Transactional
    public void checkWateringNeeds() {
        LocalDateTime now = LocalDateTime.now();
        List<WateringNeed> needs = wateringNeedRepository.findByNextWateringDateBefore(now);

        for (WateringNeed need : needs) {
            emailService.sendWateringReminder(
                    need.getPlant().getOwner().getEmail(),
                    need
            );

            // Mettre à jour la prochaine date d'arrosage
            need.setNextWateringDate(now.plusDays(need.getFrequencyInDays()));
            wateringNeedRepository.save(need);
        }
    }
}
