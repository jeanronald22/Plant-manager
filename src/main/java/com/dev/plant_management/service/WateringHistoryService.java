package com.dev.plant_management.service;

import com.dev.plant_management.entity.WateringHistory;
import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.payload.request.WateringHistoryRequest;
import com.dev.plant_management.payload.request.WateringNeedRequest;
import com.dev.plant_management.payload.response.WateringHistoryResponse;
import com.dev.plant_management.payload.response.WateringNeedResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WateringHistoryService {
    WateringHistoryResponse saveHistory(WateringHistoryRequest history, Plant plant);
    Optional<WateringHistoryResponse> findById(Long id);
    List<WateringHistoryResponse> findByPlant(Plant plant);
    List<WateringHistoryResponse> findAll();
    void delete(Long id);
}
