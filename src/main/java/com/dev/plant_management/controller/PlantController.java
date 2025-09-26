package com.dev.plant_management.controller;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.UserEntity;
import com.dev.plant_management.exceptions.PlantErrorCode;
import com.dev.plant_management.exceptions.PlantNotFoundException;
import com.dev.plant_management.service.CustomUserDetailService;
import com.dev.plant_management.service.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_PLANT_NOT_FOUND;
import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_USER_NOT_FOUND;

@RestController
@RequestMapping("/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;
    private final CustomUserDetailService userService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Plant> createPlant(@Valid  @PathVariable UUID userId, @RequestBody Plant plant) {
        UserEntity owner = userService.findById(userId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_USER_NOT_FOUND));

        Plant savedPlant = plantService.savePlant(plant, owner);
        return ResponseEntity.ok(savedPlant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@Valid @PathVariable Long id) {
        return plantService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_PLANT_NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Plant>> getPlantsByUser(@PathVariable UUID userId) {
        UserEntity owner = userService.findById(userId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_USER_NOT_FOUND));
        return ResponseEntity.ok(plantService.findByOwner(owner));
    }

    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        return ResponseEntity.ok(plantService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
