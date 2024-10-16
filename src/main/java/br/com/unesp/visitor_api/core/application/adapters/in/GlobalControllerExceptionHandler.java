package br.com.unesp.visitor_api.core.application.adapters.in;

import br.com.unesp.visitor_api.core.application.adapters.DefaultApiResponse;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.DuplicateDocumentException;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.NotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateDocumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DefaultApiResponse<Void> handleDuplicateDocumentException(DuplicateDocumentException ex) {
        return new DefaultApiResponse<>(ex.getMessage(), null);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DefaultApiResponse<Void> handleNotFoundException(NotFoundException ex) {
        return new DefaultApiResponse<>(ex.getMessage(), null);
    }
}
