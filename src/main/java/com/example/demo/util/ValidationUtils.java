package com.example.demo.util;

import com.example.demo.exception.ValidationException;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationUtils {
    
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");

    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email cannot be null or empty");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Invalid email format");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters long");
        }
    }

    public static void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new ValidationException(fieldName + " cannot be null");
        }
    }

    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " cannot be null or empty");
        }
    }

    public static void validateFutureDate(LocalDate date, String fieldName) {
        if (date != null && date.isBefore(LocalDate.now())) {
            throw new ValidationException(fieldName + " cannot be in the past");
        }
    }

    public static void validatePositiveNumber(Number value, String fieldName) {
        if (value == null || value.doubleValue() <= 0) {
            throw new ValidationException(fieldName + " must be a positive number");
        }
    }
}