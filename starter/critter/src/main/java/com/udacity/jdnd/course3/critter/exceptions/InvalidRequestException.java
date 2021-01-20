package com.udacity.jdnd.course3.critter.exceptions;

public class InvalidRequestException extends RuntimeException {

    private String errorMessage;
    private String errorCode;

    public InvalidRequestException(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = "400";
    }

    public InvalidRequestException(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public InvalidRequestException(String message, String errorMessage, String errorCode) {
        super(message);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

}
