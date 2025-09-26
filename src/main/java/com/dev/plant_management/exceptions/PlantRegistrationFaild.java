package com.dev.plant_management.exceptions;

public class PlantRegistrationFaild extends PlantException{
    public PlantRegistrationFaild(PlantErrorCode errorCode){
        super(errorCode);
    }
}
