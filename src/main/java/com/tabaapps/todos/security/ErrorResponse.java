package com.tabaapps.todos.security;

public class ErrorResponse {

    private int code;
    private String message;

    ErrorResponse(String message) {
        this.message = message;
    }

    ErrorResponse(int code, String message) {
        this(message);
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
