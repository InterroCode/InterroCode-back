package com.example.demo.controller;

import com.example.demo.dto.SignInRequestDTO;
import com.example.demo.dto.TokenResponseDTO;
import com.example.demo.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserControllerTH {

    private final UserService userService;

    @PostMapping("/api/auth/signin")
    public ResponseEntity<TokenResponseDTO> signIn(@RequestBody SignInRequestDTO request) throws JsonProcessingException {
        TokenResponseDTO response = userService.signIn(request);

        return new ResponseEntity<TokenResponseDTO>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/auth/signup")
    public String signUp() {
        return "hello";
    }
}
