package com.dev.plant_management.service.implementations;


import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.WateringNeed;
import com.dev.plant_management.repository.WateringNeedRepository;
import com.dev.plant_management.service.WateringNeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WateringNeedServiceImpl implements WateringNeedService {

    private final WateringNeedRepository wateringNeedRepository;

    @Override
    public WateringNeed saveNeed(WateringNeed need, Plant plant) {
        need.setPlant(plant);
        return wateringNeedRepository.save(need);
    }

    @Override
    public Optional<WateringNeed> findById(Long id) {
        return wateringNeedRepository.findById(id);
    }

    @Override
    public List<WateringNeed> findByPlant(Plant plant) {
        return wateringNeedRepository.findByPlant(plant);
    }

    @Override
    public List<WateringNeed> findAll() {
        return wateringNeedRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        wateringNeedRepository.deleteById(id);
    }
}
