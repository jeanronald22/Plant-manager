package com.dev.plant_management.service.implementations;


import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.UserEntity;
import com.dev.plant_management.repository.PlantRepository;
import com.dev.plant_management.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    @Override
    public Plant savePlant(Plant plant, UserEntity owner) {
        plant.setOwner(owner);
        return plantRepository.save(plant);
    }

    @Override
    public Optional<Plant> findById(Long id) {
        return plantRepository.findById(id);
    }

    @Override
    public List<Plant> findByOwner(UserEntity owner) {
        return plantRepository.findByOwner(owner);
    }

    @Override
    public List<Plant> findAll() {
        return plantRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        plantRepository.deleteById(id);
    }
}
