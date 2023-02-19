package com.monofi.controller;

import com.monofi.auth.TokenProvider;
import com.monofi.dto.AccessTokenDto;
import com.monofi.dto.LoginDto;
import com.monofi.dto.RegistrationDto;
import com.monofi.dto.SmsRequestDto;
import com.monofi.model.SmsVerificationToken;
import com.monofi.model.User;
import com.monofi.model.EmailVerificationToken;
import com.monofi.service.EmailSenderService;
import com.monofi.service.SmsSenderService;
import com.monofi.service.SmsVerificationTokenService;
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

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final Duration ONE_DAY = Duration.ofDays(1);
    private static final Duration ONE_WEEK = Duration.ofDays(7);

    private final SmsSenderService smsSenderService;
    private final EmailSenderService emailSenderService;
    private final TokenProvider jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final SmsVerificationTokenService smsVerificationTokenService;

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
    public ResponseEntity<AccessTokenDto> register(@Valid @RequestBody RegistrationDto dto) throws Exception {
        log.trace("Sign up request with email {}", dto.getUsername());
        EmailVerificationToken emailVerificationToken = userService.register(dto);
        emailSenderService
                .sendEmail(dto.getUsername(),
                        "Verification email",
                        "http://localhost:8082/auth/confirm?token="+ emailVerificationToken.getToken());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/verify/email")
    public ResponseEntity<User> verifyEmail(@RequestParam("token") AccessTokenDto accessTokenDto){
        User user = userService.verifyEmail(accessTokenDto.getToken());
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @GetMapping("/verify/number")
    public ResponseEntity<User> verifyPhoneNumber(@RequestParam("token") AccessTokenDto accessTokenDto){
        User user = userService.verifyPhoneNumber(accessTokenDto.getToken());
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @PostMapping("/sms")
    public ResponseEntity<Void> sendVerificationSms(@Valid @RequestBody SmsRequestDto smsRequestDto){
        Random random = new Random();
        Integer number = random.nextInt(998999)+1000;
        SmsVerificationToken smsVerificationToken = smsVerificationTokenService.save(
                SmsVerificationToken.builder()
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .phoneNumber(smsRequestDto.getPhoneNumber())
                .number(number)
                .user(userService.findById(new Long(smsRequestDto.getUserId())))
                .build());
        smsSenderService.sendSms(smsVerificationToken);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getAuthenticatedUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
