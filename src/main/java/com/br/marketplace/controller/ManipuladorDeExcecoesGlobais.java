package com.br.marketplace.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.validation.FieldError;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ManipuladorDeExcecoesGlobais {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> manipularExcecoesDeValidacao(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.append(String.format("Campo '%s': %s%n", fieldName, errorMessage));
        });
        return ResponseEntity.badRequest().body(errors.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> tratarExcecoesViolacaoRestricoes(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

