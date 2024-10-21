package com.nodirverse.dictionary.service;

import com.nodirverse.dictionary.dto.request.AuthDTO;
import com.nodirverse.dictionary.dto.response.JwtResponse;
import com.nodirverse.dictionary.entity.UserEntity;
import com.nodirverse.dictionary.exception.DataNotFoundException;
import com.nodirverse.dictionary.repository.UserRepository;
import com.nodirverse.dictionary.service.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public JwtResponse signIn(AuthDTO request) {
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new JwtResponse(jwtUtil.generateToken(user));
        }
        throw new AuthenticationCredentialsNotFoundException("password didn't match");
    }
}
