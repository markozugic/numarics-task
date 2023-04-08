package com.numarics.gameservice.exceptions;

import com.numarics.gameservice.exceptions.custom.GameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Map<String, String>> handleWebClientException(WebClientResponseException e) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(GameNotFoundException e) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
