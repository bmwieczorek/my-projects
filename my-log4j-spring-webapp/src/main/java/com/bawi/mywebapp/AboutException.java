package com.bawi.mywebapp;

public class AboutException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AboutException() {
    }

    public AboutException(String message) {
        super(message);
    }
}
