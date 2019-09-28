package com.stady.FirstGradleProject.errors;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Logger logger;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request) {
       ExceptionResponseFormat response = new ExceptionResponseFormat(new Date(),e.getMessage(), request.getDescription(false));
       logger.error(e.getMessage(),e);

       return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exception e, WebRequest request) {
        ExceptionResponseFormat response = new ExceptionResponseFormat(new Date(),e.getMessage(), request.getDescription(false));
        logger.error(e.getMessage(),e);

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponseFormat response = new ExceptionResponseFormat(new Date(),"Validation Failed!", ex.getBindingResult().toString());
        logger.error(ex.getMessage(),ex);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);    }
}
