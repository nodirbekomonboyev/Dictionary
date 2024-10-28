package com.nodirverse.dictionary.controller;


import com.nodirverse.dictionary.dto.request.AuthDTO;
import com.nodirverse.dictionary.dto.response.JwtResponse;
import com.nodirverse.dictionary.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@Valid @RequestBody AuthDTO request) {
        return ResponseEntity.ok(userService.signIn(request));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JwtResponse> signUp(@Valid @RequestBody AuthDTO request){
        return ResponseEntity.ok(userService.signUp(request));
    }
}
