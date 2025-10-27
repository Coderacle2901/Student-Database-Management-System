package com.rapheal.student_management_system.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// Exception Handlers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handler for handling MethodArgumentNotValidException exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
       var fieldErrors =  ex.getBindingResult().getFieldErrors();
       fieldErrors.forEach(error -> errors.put(error.getField(),error.getDefaultMessage())
       );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }

   // Handler for handling Data integrity violation
    @ExceptionHandler(DataIntegrityViolationException.class)
   public ResponseEntity<Map<String,String>> handleDataIntegrityErrors(DataIntegrityViolationException ex){
        Map<String,String> error = new HashMap<>();
        error.put("error","Data integrity violation: " + ex.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Handler for handling NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String,String>> handleNoSuchElementException(NoSuchElementException ex){
        Map<String,String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    // handler for EmptyResultDataAccessException
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Map<String,String>> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex){
        Map<String,String> error = new HashMap<>();
        error.put("error",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // handler for handling unknown or un-specified exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleException (Exception ex){
     Map<String,String> error = new HashMap<>();
     error.put("error","an unexpected error has occurred");
     error.put("Details",ex.getMessage());
     return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

   }



