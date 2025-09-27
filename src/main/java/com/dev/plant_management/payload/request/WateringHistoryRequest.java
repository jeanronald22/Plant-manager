package com.dev.plant_management.payload.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WateringHistoryRequest {
    private LocalDateTime wateringDate = LocalDateTime.now();
    private String notes;
}
