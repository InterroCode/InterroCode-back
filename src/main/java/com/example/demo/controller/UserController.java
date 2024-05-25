package com.example.demo.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.dto.SignUpResponse;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody SignUpRequest signUpRequest) {
        
        SignUpResponse response = userService.register(signUpRequest);
        if (response == null) {
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("회원가입에 실패했습니다.")
                    .build());
        }
        return ResponseEntity.ok().body(ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("회원가입에 성공했습니다.")
                .data(response)
                .build());
    }

    @PostMapping("/checkEmailDuplicate")
    public ResponseEntity<ApiResponse> checkEmailDuplicate(@RequestBody SignUpRequest signUpRequest) {
        if (userService.checkEmailDuplicate(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("중복된 이메일입니다.")
                    .build());
        }
        return ResponseEntity.ok().body(ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("사용 가능한 이메일입니다.")
                .build());
    }
}
