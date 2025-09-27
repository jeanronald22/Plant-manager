package com.dev.plant_management.controller;

import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.UserEntity;
import com.dev.plant_management.exceptions.PlantErrorCode;
import com.dev.plant_management.exceptions.PlantNotFoundException;
import com.dev.plant_management.payload.request.PlantRequest;
import com.dev.plant_management.payload.response.PlantResponse;
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
    public ResponseEntity<PlantResponse> createPlant(@PathVariable UUID userId, @RequestBody PlantRequest request) {
        UserEntity owner = userService.findById(userId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_USER_NOT_FOUND));

        PlantResponse savedPlant = plantService.savePlant(request, owner);
        return ResponseEntity.ok(savedPlant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantResponse> getPlantById(@Valid @PathVariable Long id) {
        return plantService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_PLANT_NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlantResponse>> getPlantsByUser(@PathVariable UUID userId) {
        UserEntity owner = userService.findById(userId)
                .orElseThrow(() -> new PlantNotFoundException(DATA_ERROR_USER_NOT_FOUND));
        return ResponseEntity.ok(plantService.findByOwner(owner));
    }

    @GetMapping
    public ResponseEntity<List<PlantResponse>> getAllPlants() {
        return ResponseEntity.ok(plantService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
