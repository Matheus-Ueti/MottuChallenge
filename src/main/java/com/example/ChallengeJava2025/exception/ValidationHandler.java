package com.example.ChallengeJava2025.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    record ValidationErrorMessage(String field, String message) {
        public ValidationErrorMessage(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ValidationErrorMessage> handle(MethodArgumentNotValidException e) {
        return e.getFieldErrors()
                .stream()
                .map(ValidationErrorMessage::new)
                .toList();
    }

    @ExceptionHandler(org.springframework.web.server.ResponseStatusException.class)
    @ResponseStatus
    public String handle(ResponseStatusException e) {
        return e.getReason();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception e) {
        return "Erro interno: " + e.getMessage();
    }
} 