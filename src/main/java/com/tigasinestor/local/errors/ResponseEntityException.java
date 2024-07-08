package com.tigasinestor.local.errors;

import com.tigasinestor.local.model.dto.classbased.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseEntityException extends ResponseEntityExceptionHandler {

    //Arroja una respuesta al cliente si se presenta la excepción dinámica PresentException
    @ExceptionHandler(PresentException.class)
    public ResponseEntity<ErrorResponseDTO> errorResponse(PresentException ex) {
        ErrorResponseDTO message = new ErrorResponseDTO(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(message);
    }

    // Arroja una lista de errores al cliente si se presenta una excepción de validación
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
