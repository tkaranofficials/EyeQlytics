package com.example.EyeQlytics.EyeQlytics.dto;

import org.springframework.http.HttpStatus;

public class ResponseWrapper<T> {
    private boolean success;
    private HttpStatus status;
    private String message;
    private T data;

    public ResponseWrapper(boolean success, HttpStatus status, String message, T data) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
