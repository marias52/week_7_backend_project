package com.backend_project.backend_hobby_project.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class APIException {

    private String message;
    private int statusCode;
    private HttpStatus httpStatus;
    private ZonedDateTime timeStamp;

    public APIException(String message, int statusCode, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
