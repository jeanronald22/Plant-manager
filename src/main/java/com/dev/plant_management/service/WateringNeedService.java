package com.dev.plant_management.service;

import com.dev.plant_management.entity.WateringNeed;
import com.dev.plant_management.entity.Plant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WateringNeedService {
    WateringNeed saveNeed(WateringNeed need, Plant plant);
    Optional<WateringNeed> findById(Long id);
    List<WateringNeed> findByPlant(Plant plant);
    List<WateringNeed> findAll();
    void delete(Long id);
}
