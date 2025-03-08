package com.example.test_jala.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String errorMessage){
        super(errorMessage);
    }
}
