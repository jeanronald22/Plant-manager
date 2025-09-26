package com.dev.plant_management.exceptions;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class PlantException extends RuntimeException {
    private PlantErrorCode error;
    private final String message;
    private final Timestamp timestamp;

    public PlantException(PlantErrorCode error){
        super(error.getCode()+" : "+error.getLabel());
        this.message = error.getCode()+" : "+error.getLabel();
        this.error = error;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public PlantException(String message){
        super(message);
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
    public PlantException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.error = null;
    }
}

