package com.nodirverse.dictionary.service;

import com.nodirverse.dictionary.dto.request.AuthDTO;
import com.nodirverse.dictionary.dto.request.TokenRefreshDTO;
import com.nodirverse.dictionary.dto.response.JwtResponse;
import com.nodirverse.dictionary.entity.UserEntity;
import com.nodirverse.dictionary.entity.UserRole;
import com.nodirverse.dictionary.exception.DataAlreadyExistsException;
import com.nodirverse.dictionary.exception.DataNotFoundException;
import com.nodirverse.dictionary.repository.UserRepository;
import com.nodirverse.dictionary.service.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
            List<String> tokens = jwtUtil.generateToken(user);
            return new JwtResponse(tokens.get(0), tokens.get(1));
        }
        throw new AuthenticationCredentialsNotFoundException("password didn't match");
    }


    public JwtResponse signUp(AuthDTO request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new DataAlreadyExistsException("username already exists!");
        }
        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();
        userRepository.save(userEntity);
        List<String> tokens = jwtUtil.generateToken(userEntity);
        return new JwtResponse(tokens.get(0), tokens.get(1));
    }

    public JwtResponse tokenRefresh(TokenRefreshDTO request) {
        String id = jwtUtil.extractToken(request.getRefreshToken()).getBody().getSubject();
        System.out.println(id);
        UserEntity user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("user not found!!!"));
        if(jwtUtil.checkRefreshToken(user, request.getRefreshToken())){
            List<String> tokens = jwtUtil.generateToken(user);
            return new JwtResponse(tokens.get(0), tokens.get(1));
        }
        throw new AuthenticationCredentialsNotFoundException("refresh token didn't match");
    }
}
