package com.diner.diner.config;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura errores de @Valid (NotNull, Size, Email, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    // 2. Captura errores de Base de Datos (Como el nombre duplicado ORA-00001)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        
        // Verificamos si es un error de duplicado (Unique Constraint)
        if (ex.getMessage() != null && ex.getMessage().contains("UK")) {
            errors.put("nombre", "Ya existe un restaurante con ese nombre");
        } else {
            errors.put("error", "Error de integridad de datos: " + ex.getMostSpecificCause().getMessage());
        }
        
        return ResponseEntity.status(409).body(errors); // 409 Conflict
    }
}