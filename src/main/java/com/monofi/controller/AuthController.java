package com.monofi.controller;

import com.monofi.auth.TokenProvider;
import com.monofi.dto.AccessTokenDto;
import com.monofi.dto.LoginDto;
import com.monofi.dto.RegistrationDto;
import com.monofi.exception.EmailAlreadyUsedException;
import com.monofi.model.User;
import com.monofi.model.VerificationToken;
import com.monofi.service.EmailSenderService;
import com.monofi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final Duration ONE_DAY = Duration.ofDays(1);
    private static final Duration ONE_WEEK = Duration.ofDays(7);

    private final EmailSenderService emailSenderService;
    private final TokenProvider jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AccessTokenDto> authorize(@RequestBody LoginDto loginDto) {
        log.trace("Login request by user {}", loginDto.getPassword());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtService.generateToken(authentication);
        return new ResponseEntity<>(new AccessTokenDto(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AccessTokenDto> register(@RequestBody RegistrationDto dto) throws Exception {
        log.trace("Sign up request with email {}", dto.getUsername());
        VerificationToken verificationToken = userService.register(dto);
        emailSenderService
                .sendEmail(dto.getUsername(),
                        "Verification email",
                        "http://localhost:8082/auth/confirm?token="+verificationToken.getToken());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public ResponseEntity<User> confirm(@RequestParam("token") AccessTokenDto accessTokenDto){
        userService.confirm(accessTokenDto.getToken());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getAuthenticatedUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
