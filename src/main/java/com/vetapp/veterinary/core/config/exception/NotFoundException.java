package com.vetapp.veterinary.core.config.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}

