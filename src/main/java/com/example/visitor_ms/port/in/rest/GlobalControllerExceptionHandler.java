package com.example.visitor_ms.port.in.rest;

import com.example.visitor_ms.domain.exception.InvalidAccessException;
import com.example.visitor_ms.domain.exception.InvalidAddressException;
import com.example.visitor_ms.domain.exception.InvalidCompanyException;
import com.example.visitor_ms.domain.exception.InvalidContactException;
import com.example.visitor_ms.domain.exception.InvalidPersonException;
import com.example.visitor_ms.domain.exception.NotFoundException;
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

    @ExceptionHandler(InvalidPersonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultApiResponse<Void> handleInvalidPersonException(InvalidPersonException ex) {
        return new DefaultApiResponse<>(ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultApiResponse<Void> handleInvalidAccessException(InvalidAccessException ex) {
        return new DefaultApiResponse<>(ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidAddressException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultApiResponse<Void> handleInvalidAddressException(InvalidAddressException ex) {
        return new DefaultApiResponse<>(ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidContactException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultApiResponse<Void> handleInvalidContactException(InvalidContactException ex) {
        return new DefaultApiResponse<>(ex.getMessage(), null);
    }


    @ExceptionHandler(InvalidCompanyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidCompanyException(InvalidCompanyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}