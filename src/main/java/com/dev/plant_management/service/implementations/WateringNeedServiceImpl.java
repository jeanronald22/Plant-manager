package com.dev.plant_management.service.implementations;


import com.dev.plant_management.controller.WateringNeedController;
import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.WateringNeed;
import com.dev.plant_management.payload.request.WateringNeedRequest;
import com.dev.plant_management.payload.response.WateringNeedResponse;
import com.dev.plant_management.repository.WateringNeedRepository;
import com.dev.plant_management.service.WateringNeedService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WateringNeedServiceImpl implements WateringNeedService {

    private final WateringNeedRepository wateringNeedRepository;
    private final ModelMapper mapper;

    @Override
    public WateringNeedResponse saveNeed(WateringNeedRequest need, Plant plant) {
        WateringNeed wateringNeed = mapper.map(need, WateringNeed.class);
        wateringNeed.setPlant(plant);
        return mapper.map(wateringNeedRepository.save(wateringNeed), WateringNeedResponse.class);
    }

    @Override
    public Optional<WateringNeedResponse> findById(Long id) {
        return Optional.ofNullable(mapper.map(wateringNeedRepository.findById(id), WateringNeedResponse.class));
    }

    @Override
    public List<WateringNeedResponse> findByPlant(Plant plant) {
        return wateringNeedRepository.findByPlant(plant).stream().map(it ->
                mapper.map(it, WateringNeedResponse.class)).toList();
    }

    @Override
    public List<WateringNeedResponse> findAll() {
        return wateringNeedRepository.findAll().stream().map(it ->
                mapper.map(it, WateringNeedResponse.class)).toList();
    }

    @Override
    public void delete(Long id) {
        wateringNeedRepository.deleteById(id);
    }
}
