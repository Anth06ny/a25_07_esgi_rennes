package org.example.a25_07_esgi_rennes.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.a25_07_esgi_rennes.model.ErrorBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //On transforme toutes les exceptions en ErrorBean retourné sous forme JSON
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorBean> handleGeneric(Exception ex, HttpServletRequest request) throws Exception {

        if (ex instanceof HttpRequestMethodNotSupportedException ||
                ex instanceof NoHandlerFoundException ||
                ex instanceof HttpMediaTypeNotSupportedException) {
            // Laisser Spring gérer ces erreurs
            throw ex;
        }

        ErrorBean error = new ErrorBean(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Une erreur interne est survenue",
                request.getRequestURI(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //On peut appliquer un traitement spécifique pour certaine exception, ici celle du validator
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBean> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorBean error = new ErrorBean(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                "Invalid request body",
                request.getRequestURI(),
                errors
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
