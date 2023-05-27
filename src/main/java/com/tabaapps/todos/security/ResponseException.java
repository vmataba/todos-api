package com.tabaapps.todos.security;

import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {

    private HttpStatus httpStatus;

    public static final String CODE_BAD_CREDENTIALS = "BAD_CREDENTIALS";

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
