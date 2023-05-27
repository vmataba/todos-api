package com.tabaapps.todos.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponse> handleException(ResponseException exception) {
         ErrorResponse response = new ErrorResponse(exception.getHttpStatus().value(), exception.getMessage());
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }
}
