package com.example.demo.controller;

import com.example.demo.dto.SignInRequestDTO;
import com.example.demo.dto.TokenResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserControllerTH {

    @PostMapping("/api/auth/signin")
    public ResponseEntity<TokenResponseDTO> signIn(@RequestBody SignInRequestDTO request) {
        return null;
    }
}
