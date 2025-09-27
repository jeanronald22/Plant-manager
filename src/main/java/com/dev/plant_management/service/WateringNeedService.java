package com.dev.plant_management.service;

import com.dev.plant_management.entity.WateringNeed;
import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.payload.request.WateringNeedRequest;
import com.dev.plant_management.payload.response.WateringNeedResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WateringNeedService {
    WateringNeedResponse saveNeed(WateringNeedRequest need, Plant plant);
    Optional<WateringNeedResponse> findById(Long id);
    List<WateringNeedResponse> findByPlant(Plant plant);
    List<WateringNeedResponse> findAll();
    void delete(Long id);
}
