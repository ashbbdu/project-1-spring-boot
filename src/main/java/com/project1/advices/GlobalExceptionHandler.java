package com.project1.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> resourceNotFound (NoSuchElementException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> resourceNotFound (NoSuchElementException e) {
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        return new ResponseEntity<>(apiError , HttpStatus.NOT_FOUND);
    }
}
