package dev.javokhir.talabaguide.exceptions;

import dev.javokhir.talabaguide.exceptions.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateErrors(DuplicateResourceException ex,
                                                               HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.builder()
                        .date(new Date())
                        .error(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .path(req.getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundErrors(EntityNotFoundException ex,
                                                               HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                        .date(new Date())
                        .error(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .path(req.getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler(MissingIdException.class)
    public ResponseEntity<ErrorResponse> handleMissingIdErrors(MissingIdException ex,
                                                               HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.builder()
                        .date(new Date())
                        .error(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .path(req.getRequestURI())
                        .build()
        );
    }
}
