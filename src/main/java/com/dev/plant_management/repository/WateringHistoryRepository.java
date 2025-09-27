package com.dev.plant_management.repository;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.WateringHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WateringHistoryRepository extends JpaRepository<WateringHistory, Long> {
    List<WateringHistory> findByPlant(Plant plant);
}
