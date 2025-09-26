package com.dev.plant_management.controller;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.WateringHistory;
import com.dev.plant_management.exceptions.PlantErrorCode;
import com.dev.plant_management.exceptions.PlantNotFoundException;
import com.dev.plant_management.payload.request.WateringHistoryRequest;
import com.dev.plant_management.payload.response.PlantResponse;
import com.dev.plant_management.payload.response.WateringHistoryResponse;
import com.dev.plant_management.service.PlantService;
import com.dev.plant_management.service.WateringHistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_PLANT_NOT_FOUND;
import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_WATERINGHISTORY_NOT_FOUND;

@RestController
@RequestMapping("/watering-history")
@RequiredArgsConstructor
public class WateringHistoryController {

    private final WateringHistoryService wateringHistoryService;
    private final PlantService plantService;
    private final ModelMapper mapper;

    @PostMapping("/create/{plantId}")
    public ResponseEntity<WateringHistoryResponse> createHistory(@PathVariable Long plantId, @RequestBody WateringHistoryRequest history) {
        PlantResponse plantResponse = plantService.findById(plantId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_PLANT_NOT_FOUND));
        Plant plant = mapper.map(plantResponse, Plant.class);
        return ResponseEntity.ok(wateringHistoryService.saveHistory(history, plant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WateringHistoryResponse> getHistoryById(@PathVariable Long id) {
        return wateringHistoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_WATERINGHISTORY_NOT_FOUND));
    }

    @GetMapping("/plant/{plantId}")
    public ResponseEntity<List<WateringHistoryResponse>> getHistoriesByPlant(@PathVariable Long plantId) {
        PlantResponse plantResponse = plantService.findById(plantId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_PLANT_NOT_FOUND));
        Plant plant = mapper.map(plantResponse, Plant.class);
        return ResponseEntity.ok(wateringHistoryService.findByPlant(plant));
    }

    @GetMapping
    public ResponseEntity<List<WateringHistoryResponse>> getAllHistories() {
        return ResponseEntity.ok(wateringHistoryService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        wateringHistoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
