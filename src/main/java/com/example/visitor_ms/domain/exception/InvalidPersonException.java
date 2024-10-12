package com.example.visitor_ms.domain.exception;

public class InvalidPersonException extends RuntimeException{
    public InvalidPersonException(String message) {
        super(message);
    }
}
