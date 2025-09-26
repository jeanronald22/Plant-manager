package com.dev.plant_management.service.implementations;


import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.UserEntity;
import com.dev.plant_management.payload.request.PlantRequest;
import com.dev.plant_management.payload.response.PlantResponse;
import com.dev.plant_management.repository.PlantRepository;
import com.dev.plant_management.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final ModelMapper mapper;

    @Override
    public PlantResponse savePlant(PlantRequest request, UserEntity owner) {
        Plant plant = mapper.map(request, Plant.class);
        plant.setOwner(owner);
        Plant saved = plantRepository.save(plant);

        return mapper.map(saved, PlantResponse.class);
    }

    @Override
    public Optional<PlantResponse> findById(Long id) {
        return Optional.ofNullable(mapper.map(plantRepository.findById(id), PlantResponse.class));
    }

    @Override
    public List<PlantResponse> findByOwner(UserEntity owner) {
        return plantRepository.findByOwner(owner).stream().map(it ->
                        mapper.map(it, PlantResponse.class)
                ).toList();
    }

    @Override
    public List<PlantResponse> findAll() {
        return plantRepository.findAll().stream().map(it->
                mapper.map(it, PlantResponse.class)
        ).toList();
    }

    @Override
    public void delete(Long id) {
        plantRepository.deleteById(id);
    }
}
