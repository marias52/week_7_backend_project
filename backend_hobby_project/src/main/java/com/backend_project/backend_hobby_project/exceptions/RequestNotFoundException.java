package com.backend_project.backend_hobby_project.exceptions;

public class RequestNotFoundException extends RuntimeException{
    public RequestNotFoundException(String message) {
        super(message);
    }
}
