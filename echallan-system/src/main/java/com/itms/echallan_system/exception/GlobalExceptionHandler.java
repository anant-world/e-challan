package com.itms.echallan_system.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResouceNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);


    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateExceptionHandle.class)
    public ResponseEntity<String> handleDuplicateTransaction(DuplicateExceptionHandle dx){
        return new ResponseEntity<>(dx.getMessage(),HttpStatus.CONFLICT);
    }
}
