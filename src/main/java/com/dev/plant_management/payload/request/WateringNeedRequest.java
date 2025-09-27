package com.dev.plant_management.payload.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class WateringNeedRequest {
    private int frequencyInDays;
    private double quantityInLiters;
}
