package com.backend_project.backend_hobby_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GeneralAPIExceptionHandler {

    private String zoneID = "Europe/London";
    HttpStatus httpStatus;

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {

        httpStatus = HttpStatus.BAD_REQUEST;

        APIException apiException = new APIException(
                e.getMessage(),
                httpStatus.value(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of(zoneID))
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {RequestNotFoundException.class})
    public ResponseEntity<Object> handleRequestNotFoundException(RequestNotFoundException e) {

        httpStatus = HttpStatus.NOT_FOUND;

        APIException notFoundException = new APIException(
                e.getMessage(),
                httpStatus.value(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of(zoneID))
        );

        return new ResponseEntity<>(notFoundException, httpStatus);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleJSONException(HttpMessageNotReadableException e) {

        httpStatus = HttpStatus.BAD_REQUEST;

        APIException badJSONException = new APIException(
                "Issue with JSON payload",
                httpStatus.value(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of(zoneID))
        );

        return new ResponseEntity<>(badJSONException, httpStatus);
    }
}
