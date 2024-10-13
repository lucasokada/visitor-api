package com.example.visitor_ms.domain.exception;

public class InvalidCompanyException extends RuntimeException {
    public InvalidCompanyException(String message) {
        super(message);
    }
}
