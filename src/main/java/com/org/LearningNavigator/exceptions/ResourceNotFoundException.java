package com.org.LearningNavigator.exceptions;


public class ResourceNotFoundException extends RuntimeException{

    private static final String DEFAULT_MSG = "Resource not found....";
    public ResourceNotFoundException(){
        super(DEFAULT_MSG);
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
