package com.example.EyeQlytics.EyeQlytics.exception;

import com.example.EyeQlytics.EyeQlytics.dto.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseWrapper<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseWrapper<>(false, HttpStatus.NOT_FOUND, ex.getMessage(), null));
    }

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ResponseWrapper<String>> handleApiRequestException(ApiRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ResponseWrapper<>(false, HttpStatus.BAD_GATEWAY, ex.getMessage(), null));
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<ResponseWrapper<String>> handleDatabaseOperationException(DatabaseOperationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseWrapper<>(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<String>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseWrapper<>(false, HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred", null));
    }
}
