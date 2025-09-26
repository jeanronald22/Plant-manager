package com.dev.plant_management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlantErrorCode {
    DATA_ERROR_INVALID_PASSWORD_FORMAT("400-009", "Password must be at least 8 characters long, Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"),

    DATA_ERROR_USER_NOT_FOUND("404-002", "User not found"),
    DATA_ERROR_USERNAME_ALREADY_TAKEN("409-001", "Username is already taken"),
    DATA_ERROR_EMAIL_ALREADY_TAKEN("409-002", "Email is already taken"),
    DATA_ERROR_UNKNOWN_CONSTRAINT("409-999", "A database constraint violation occurred"),
    DATA_ERROR_INVALID_CREDENTIALS("401-001", "Invalid email or password"),

    DATA_ERROR_JSON_CONVERSION("400-010", "Error during JSON data conversion."),
    DATA_ERROR_INVALID_TIME_FORMAT("400-011", "Invalid time format provided."),
    DATA_ERROR_REGISTRATION_FAILED("400-012", "User registration failed");


    private final String code;
    private final String label;
}