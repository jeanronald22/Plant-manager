package com.dev.plant_management.controller;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.exceptions.PlantNotFoundException;
import com.dev.plant_management.payload.request.WateringNeedRequest;
import com.dev.plant_management.payload.response.PlantResponse;
import com.dev.plant_management.payload.response.WateringNeedResponse;
import com.dev.plant_management.service.PlantService;
import com.dev.plant_management.service.WateringNeedService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_PLANT_NOT_FOUND;
import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_WATERINGNEED_NOT_FOUND;

@RestController
@RequestMapping("/watering-needs")
@RequiredArgsConstructor
public class WateringNeedController {

    private final WateringNeedService wateringNeedService;
    private final PlantService plantService;
    private final ModelMapper mapper;

    @PostMapping("/create/{plantId}")
    public ResponseEntity<WateringNeedResponse> createNeed(@PathVariable Long plantId, @RequestBody WateringNeedRequest need) {
        PlantResponse plantResponse = plantService.findById(plantId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_PLANT_NOT_FOUND));
        Plant plant = mapper.map(plantResponse, Plant.class);
        return ResponseEntity.ok(wateringNeedService.saveNeed(need, plant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WateringNeedResponse> getNeedById(@PathVariable Long id) {
        return wateringNeedService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_WATERINGNEED_NOT_FOUND));
    }

    @GetMapping("/plant/{plantId}")
    public ResponseEntity<List<WateringNeedResponse>> getNeedsByPlant(@PathVariable Long plantId) {
        PlantResponse plantResponse = plantService.findById(plantId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_PLANT_NOT_FOUND));
        Plant plant = mapper.map(plantResponse, Plant.class);
        return ResponseEntity.ok(wateringNeedService.findByPlant(plant));
    }

    @GetMapping
    public ResponseEntity<List<WateringNeedResponse>> getAllNeeds() {
        return ResponseEntity.ok(wateringNeedService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNeed(@PathVariable Long id) {
        wateringNeedService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
