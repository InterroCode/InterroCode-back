package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.global.api.Result;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Void> handleException(Exception e) {
        return new Result<>(HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }
}
