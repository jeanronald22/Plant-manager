package com.dev.plant_management.payload.response;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WateringHistoryResponse {
    private Long id;
    private LocalDateTime wateringDate;
    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
