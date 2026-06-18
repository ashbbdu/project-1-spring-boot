package com.project1.advices;

import com.project1.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> resourceNotFound (NoSuchElementException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiError> resourceNotFound (ResourceNotFoundException e) { // here we used a custom exception
//        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
//        return new ResponseEntity<>(apiError , HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleException (Exception e) {
//        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
//        return new ResponseEntity<>(apiError , HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleMethodArgumentNotValidException (MethodArgumentNotValidException exception) {
//       List<String> errors = exception.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
//        ApiError apiError = ApiError.builder().success(false)
//                .httpStatus(HttpStatus.BAD_REQUEST).message(errors.toString()).build();
//        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleMethodArgumentNotValidException (MethodArgumentNotValidException exception) {
//        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
//        ApiError apiError = ApiError.builder().success(false)
//                .httpStatus(HttpStatus.BAD_REQUEST)
//                .subErrors(errors)
//                .message("Input Validation Failed !").build();
//        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFound (ResourceNotFoundException e) { // here we used a custom exception
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException (Exception e) {
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
        return buildErrorResponseEntity(apiError);
    }


        @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException (MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .subErrors(errors)
                .message("Input Validation Failed !").build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError) , HttpStatus.BAD_REQUEST);
    }
}
