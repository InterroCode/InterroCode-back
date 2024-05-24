package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpResponse {
    private String email;
    private String nickname;

    @Builder
    public SignUpResponse(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
