package br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
