package com.dev.plant_management.payload.request;

import com.dev.plant_management.entity.Espece;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantRequest {
    private String name;
    private String imageUrl;
    private LocalDate purchaseDate;
    private Espece espece;
}
