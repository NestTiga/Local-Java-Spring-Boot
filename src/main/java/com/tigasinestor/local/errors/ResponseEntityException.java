package com.tigasinestor.local.errors;

import com.tigasinestor.local.model.dto.classbased.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityException extends ResponseEntityExceptionHandler {

    //Arroja una respuesta al cliente si se presenta la excepción dinámica PresentException
    @ExceptionHandler(PresentException.class)
    public ResponseEntity<ErrorResponseDTO> errorResponse(PresentException ex){
        ErrorResponseDTO message= new ErrorResponseDTO(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(message);
    }
}
