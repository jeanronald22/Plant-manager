package com.dev.plant_management.exceptions;

public class PlantInvalidCredentials extends PlantException{
    public PlantInvalidCredentials(PlantErrorCode errorCode){
        super(errorCode);
    }
    public PlantInvalidCredentials(String message){
        super(message);
    }
}
