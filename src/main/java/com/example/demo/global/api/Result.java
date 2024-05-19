package com.example.demo.global.api;

import org.springframework.http.HttpStatus;

public record Result<T>(HttpStatus code, String message, T data) {

}
