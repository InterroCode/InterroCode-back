package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.SignUpRequest;
import com.example.demo.domain.SignUpResponse;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponse register(SignUpRequest signUpRequest) {
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        User newUser = User.builder()
                .email(signUpRequest.getEmail())
                .password(encodedPassword)
                .nickname(signUpRequest.getNickname())
                .build();
        try{
            userRepository.save(newUser);
        }catch (Exception e){
            return null;
        }
        return SignUpResponse.builder()
                .email(signUpRequest.getEmail())
                .nickname(signUpRequest.getNickname())
                .build();
    }
}
