package br.com.lucasladeira.vuttrapi.controllers.exceptions;

import br.com.lucasladeira.vuttrapi.services.exceptions.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
        StandardError error = new StandardError(
                LocalDateTime.now(),
                404,
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
