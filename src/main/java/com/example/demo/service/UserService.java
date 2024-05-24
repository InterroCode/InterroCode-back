package com.example.demo.service;

import java.util.Optional;

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
        Optional<User> emailCheckUser = userRepository.findByEmail(signUpRequest.getEmail());
        if(emailCheckUser.isPresent()){
            return null;
        }
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

    public boolean checkEmailDuplicate(String email) {
        Optional<User> emailCheckUser = userRepository.findByEmail(email);
        return emailCheckUser.isPresent();
    }
}
