package com.example.demo.Controllers.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
// Manejamos correctamente las excepciones generadas por el validador @ValidDna
public class GlobalExceptionHandler {

    //ADN nulo o vacío, ADN no cuadrado, caracteres inválidos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    //Validaciones de @ValidDna fallan
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        // Si la validación falla, enviamos un HTTP400
        return ResponseEntity.badRequest().body("El ADN es inválido: " + e.getMessage());
    }

    //El ADN no es un arreglo
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleNotReadableExceptions(HttpMessageNotReadableException e) {
        // Si la validación falla, enviamos un HTTP400
        return ResponseEntity.badRequest().body("El ADN no es un arreglo: " + e.getMessage());
    }

    //Captura general de excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e) {
        // Si la validación falla, enviamos un HTTP400
        return ResponseEntity.badRequest().body("El ADN tiene un error: " + e.getMessage());
    }
}