package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.domain.SignUpRequest;
import com.example.demo.domain.SignUpResponse;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<SignUpResponse> register(@RequestBody SignUpRequest signUpRequest) {
        
        SignUpResponse response = userService.register(signUpRequest);
        if(response == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }
}
