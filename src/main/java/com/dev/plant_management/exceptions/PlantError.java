package com.dev.plant_management.exceptions;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlantError implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private String code;

    private String timestamp;

}