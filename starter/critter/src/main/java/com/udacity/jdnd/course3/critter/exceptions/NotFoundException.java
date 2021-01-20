package com.udacity.jdnd.course3.critter.exceptions;

public class NotFoundException extends RuntimeException {

    private String errorMessage;
    private String errorCode;

    public NotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = "404";
    }

    public NotFoundException(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public NotFoundException(String message, String errorMessage, String errorCode) {
        super(message);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

}
