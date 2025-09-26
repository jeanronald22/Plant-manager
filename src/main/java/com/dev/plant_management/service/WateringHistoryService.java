package com.dev.plant_management.service;

import com.dev.plant_management.entity.WateringHistory;
import com.dev.plant_management.entity.Plant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WateringHistoryService {
    WateringHistory saveHistory(WateringHistory history, Plant plant);
    Optional<WateringHistory> findById(Long id);
    List<WateringHistory> findByPlant(Plant plant);
    List<WateringHistory> findAll();
    void delete(Long id);
}
