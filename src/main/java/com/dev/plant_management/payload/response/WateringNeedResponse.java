package com.dev.plant_management.payload.response;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WateringNeedResponse {
    private Long id;
    private String frequencyInDays;
    private Integer quantityInLiters;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}