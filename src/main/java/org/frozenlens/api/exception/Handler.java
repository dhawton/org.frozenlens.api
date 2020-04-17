package org.frozenlens.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException e) {
        List<String> details = new ArrayList<>();

        for (ObjectError error : e.getErrors().getAllErrors())
            details.add(error.getDefaultMessage());

        ErrorResponse errorResponse = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity<>(errorResponse.toString(), HttpStatus.BAD_REQUEST);

    }
}
