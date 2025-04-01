package com.example.EyeQlytics.EyeQlytics.exception;


public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
