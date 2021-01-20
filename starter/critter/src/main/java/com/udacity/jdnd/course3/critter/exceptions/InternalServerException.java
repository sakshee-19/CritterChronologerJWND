package com.udacity.jdnd.course3.critter.exceptions;

public class InternalServerException extends RuntimeException {

    private String errorMessage;
    private String errorCode;

    public InternalServerException(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = "500";
    }

    public InternalServerException(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public InternalServerException(String message, String errorMessage, String errorCode) {
        super(message);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

}
