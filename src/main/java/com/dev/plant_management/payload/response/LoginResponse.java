package com.dev.plant_management.payload.response;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginResponse {
    private UUID userId;
    private String access;
}
