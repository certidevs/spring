package com.example.handler;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ RepositoryConstraintViolationException.class })
    public ResponseEntity<Object> handle(Exception ex, WebRequest request) {

        System.out.println("Exception: " + ex.getClass().getName());

        var repoEx = (RepositoryConstraintViolationException) ex;

        var errors = new ErrorMessage(repoEx.getErrors().getAllErrors().stream()
                .map(ObjectError::toString)
                .collect(Collectors.toList()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

