package com.example.demo.exception;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}


class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
