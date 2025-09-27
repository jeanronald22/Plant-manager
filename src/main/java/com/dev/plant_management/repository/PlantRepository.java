package com.dev.plant_management.repository;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findByOwner(UserEntity owner);
}
