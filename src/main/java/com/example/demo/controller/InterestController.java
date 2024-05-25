package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.InterestRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.service.InterestService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/interest")
public class InterestController {
    private final InterestService interestService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveInterest(@RequestBody InterestRequest request) {
        String result = interestService.saveInterest(request);
        if (!result.equals("관심분야 설정이 완료되었습니다.")) {
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(result)
                .build());
        }
        return ResponseEntity.ok(ApiResponse.builder()
            .status(HttpStatus.OK.value())
            .message(result)
            .build());
    }
    
}
