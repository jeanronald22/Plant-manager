package com.dev.plant_management.service;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlantService {
    Plant savePlant(Plant plant, UserEntity owner);
    Optional<Plant> findById(Long id);
    List<Plant> findByOwner(UserEntity owner);
    List<Plant> findAll();
    void delete(Long id);
}
