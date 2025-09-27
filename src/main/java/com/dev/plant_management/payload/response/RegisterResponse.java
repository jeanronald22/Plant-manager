package com.dev.plant_management.payload.response;

import lombok.Data;

@Data
public class RegisterResponse {
    private String username;
    private String email;
    private String id;
    private String access;

}
