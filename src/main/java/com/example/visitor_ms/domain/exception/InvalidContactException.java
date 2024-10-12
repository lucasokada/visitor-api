package com.example.visitor_ms.domain.exception;

public class InvalidContactException extends RuntimeException {
    public InvalidContactException(String message) {
        super(message);
    }
}
