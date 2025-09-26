package com.dev.plant_management.exceptions;

public class PlantNotFoundException extends PlantException{
    public PlantNotFoundException(PlantErrorCode errorCode){
        super(errorCode);
    }
}
