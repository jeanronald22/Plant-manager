package com.dev.plant_management.service;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.UserEntity;
import com.dev.plant_management.payload.request.PlantRequest;
import com.dev.plant_management.payload.response.PlantResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlantService {
    PlantResponse savePlant(PlantRequest plant, UserEntity owner);
    Optional<PlantResponse> findById(Long id);
    List<PlantResponse> findByOwner(UserEntity owner);
    List<PlantResponse> findAll();
    void delete(Long id);
}
