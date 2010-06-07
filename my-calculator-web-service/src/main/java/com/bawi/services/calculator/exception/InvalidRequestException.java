package com.bawi.services.calculator.exception;

public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = -1812767188559846376L;

    public InvalidRequestException() {
    }

    public InvalidRequestException(String message) {
        super(message);
    }

}
