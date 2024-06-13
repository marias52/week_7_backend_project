package com.backend_project.backend_hobby_project.exceptions;

public class BadJSONException extends RuntimeException{
    public BadJSONException(String message) {
        super(message);
    }
}
