package com.dev.plant_management.payload.response;


import com.dev.plant_management.entity.Espece;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlantResponse {

    private Long id;
    private String name;
    private String imageUrl;
    private LocalDate purchaseDate;
    private Espece espece;

    private List<WateringNeedResponse> wateringNeeds;
    private List<WateringHistoryResponse> wateringHistories;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
