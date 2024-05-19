package com.example.demo.service.user;

import com.example.demo.domain.User;
import com.example.demo.dto.SignInRequestDTO;
import com.example.demo.dto.TokenResponseDTO;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public TokenResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
        User user = userRepository.findByEmail(signInRequestDTO.getEmail())
                .orElseThrow(() -> null);

        boolean matches = passwordEncoder.matches(
                signInRequestDTO.getPassword(),
                user.getPassword());

        if(!matches) {
            return null;
        }
        return UserResponseDTO.of(user);
    }

}
