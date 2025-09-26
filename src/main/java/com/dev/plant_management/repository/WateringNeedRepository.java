package com.dev.plant_management.repository;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.WateringNeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WateringNeedRepository extends JpaRepository<WateringNeed, Long> {
    List<WateringNeed> findByPlant(Plant plant);
}
