package com.company.FinalProjectSaraMorita.controllers;

import com.company.FinalProjectSaraMorita.models.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> handleException(IllegalArgumentException e){
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), e.getMessage());
        errorResponse.setStatus((HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorResponse.setTimeStamp(LocalDateTime.now());
        ResponseEntity<CustomErrorResponse> response = new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);

        return response;
    }
}
