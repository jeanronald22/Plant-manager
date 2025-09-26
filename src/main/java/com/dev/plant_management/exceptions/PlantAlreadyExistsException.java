package com.dev.plant_management.exceptions;

public class PlantAlreadyExistsException extends PlantException{
    public PlantAlreadyExistsException(PlantErrorCode error){
        super(error);
    }
    public PlantAlreadyExistsException(String message){
        super(message);
    }

}
