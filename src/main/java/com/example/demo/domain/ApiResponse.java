package com.example.demo.domain;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    @Builder(toBuilder = true)
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
