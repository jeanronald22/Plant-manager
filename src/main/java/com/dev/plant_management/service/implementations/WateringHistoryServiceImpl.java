package com.dev.plant_management.service.implementations;


import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.WateringHistory;
import com.dev.plant_management.repository.WateringHistoryRepository;
import com.dev.plant_management.service.WateringHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WateringHistoryServiceImpl implements WateringHistoryService {

    private final WateringHistoryRepository wateringHistoryRepository;

    @Override
    public WateringHistory saveHistory(WateringHistory history, Plant plant) {
        history.setPlant(plant);
        return wateringHistoryRepository.save(history);
    }

    @Override
    public Optional<WateringHistory> findById(Long id) {
        return wateringHistoryRepository.findById(id);
    }

    @Override
    public List<WateringHistory> findByPlant(Plant plant) {
        return wateringHistoryRepository.findByPlant(plant);
    }

    @Override
    public List<WateringHistory> findAll() {
        return wateringHistoryRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        wateringHistoryRepository.deleteById(id);
    }
}
