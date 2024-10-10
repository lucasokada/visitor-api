package br.com.unesp.visitor_api.core.application.adapters.in;

import br.com.unesp.visitor_api.core.application.adapters.DefaultApiResponse;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.DuplicateDocumentException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConnversion(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateDocumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DefaultApiResponse<Void> handleDuplicateDocumentException(DuplicateDocumentException ex) {
        return new DefaultApiResponse<>(ex.getMessage(), null);
    }
}
