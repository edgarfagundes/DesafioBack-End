package com.example.desafio.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> excessao(IllegalArgumentException i) {
        return new ResponseEntity<>(i.getMessage(), HttpStatus.CONFLICT);
    }

}
