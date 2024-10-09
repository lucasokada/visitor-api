package br.com.unesp.visitor_api.core.application.domain.exceptions;

public class DuplicateDocumentException extends RuntimeException {
    public DuplicateDocumentException(String message) {
        super(message);
    }

    public DuplicateDocumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
