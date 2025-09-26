package com.dev.plant_management.controller;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.WateringNeed;
import com.dev.plant_management.exceptions.PlantNotFoundException;
import com.dev.plant_management.service.PlantService;
import com.dev.plant_management.service.WateringNeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_PLANT_NOT_FOUND;
import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_WATERINGNEED_NOT_FOUND;

@RestController
@RequestMapping("/watering-needs")
@RequiredArgsConstructor
public class WateringNeedController {

    private final WateringNeedService wateringNeedService;
    private final PlantService plantService;

    @PostMapping("/create/{plantId}")
    public ResponseEntity<WateringNeed> createNeed(@PathVariable Long plantId, @RequestBody WateringNeed need) {
        Plant plant = plantService.findById(plantId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_PLANT_NOT_FOUND));
        return ResponseEntity.ok(wateringNeedService.saveNeed(need, plant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WateringNeed> getNeedById(@PathVariable Long id) {
        return wateringNeedService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_WATERINGNEED_NOT_FOUND));
    }

    @GetMapping("/plant/{plantId}")
    public ResponseEntity<List<WateringNeed>> getNeedsByPlant(@PathVariable Long plantId) {
        Plant plant = plantService.findById(plantId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_PLANT_NOT_FOUND));
        return ResponseEntity.ok(wateringNeedService.findByPlant(plant));
    }

    @GetMapping
    public ResponseEntity<List<WateringNeed>> getAllNeeds() {
        return ResponseEntity.ok(wateringNeedService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNeed(@PathVariable Long id) {
        wateringNeedService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
