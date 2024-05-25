package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Interest;
import com.example.demo.domain.User;
import com.example.demo.dto.InterestRequest;
import com.example.demo.repository.InterestRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;
    private final UserRepository userRepository;

    public String saveInterest(InterestRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (!user.isPresent()) {
            return "해당 이메일을 가진 사용자가 존재하지 않습니다.";
        }
        if (request.getTask().equals(null)) {
            return "분야를 선택해주세요.";
        }
        if (request.getMainFramework().equals(null)) {
            return "주요 프레임워크를 선택해주세요.";
        }
        if (request.getMainLanguage().equals(null)) {
            return "주요 언어를 선택해주세요.";
        }
        Interest interest = Interest.builder()
            .user(user.get())
            .task(request.getTask())
            .mainLanguage(request.getMainLanguage())
            .subLanguage(request.getSubLanguage())
            .mainFramework(request.getMainFramework())
            .subFramework(request.getSubFramework())
            .build();
        interestRepository.save(interest);
        return "관심분야 설정이 완료되었습니다.";
    }
}
