package com.ap3x.ifoodtest.controllers;

import com.ap3x.ifoodtest.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public GenericResponse genericError(Exception ex)
    {
        return new GenericResponse("error", ex.getMessage());
    }
}
