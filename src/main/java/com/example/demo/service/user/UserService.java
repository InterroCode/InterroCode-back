package com.example.demo.service.user;

import com.example.demo.domain.User;
import com.example.demo.dto.SignInRequestDTO;
import com.example.demo.dto.TokenResponseDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.jwt.JwtProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public TokenResponseDTO signIn(SignInRequestDTO request) throws JsonProcessingException {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> null);

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if(!matches) {
            return null;
        }

        TokenResponseDTO response = jwtProvider.createTokensBySignIn(request);
        return response;
    }

}
