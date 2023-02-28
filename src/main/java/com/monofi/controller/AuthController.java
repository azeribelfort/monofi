package com.monofi.controller;

import com.monofi.auth.TokenProvider;
import com.monofi.dto.AccessTokenDto;
import com.monofi.dto.LoginDto;
import com.monofi.dto.RegistrationDto;
import com.monofi.dto.SmsRequestDto;
import com.monofi.model.Authority;
import com.monofi.model.SmsVerificationToken;
import com.monofi.model.User;
import com.monofi.model.EmailVerificationToken;
import com.monofi.model.enums.UserAuthority;
import com.monofi.repository.AuthorityJpaRepository;
import com.monofi.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final Duration ONE_DAY = Duration.ofDays(1);
    private static final Duration ONE_WEEK = Duration.ofDays(7);

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final EmailVerificationTokenService emailVerificationTokenService;
    private final AuthorityJpaRepository authorityJpaRepository;
    private final SmsSenderService smsSenderService;
    private final EmailSenderService emailSenderService;
    private final TokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;
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
        final String token = tokenProvider.generateToken(authentication);
        LOGGER.info("{} logged in",loginDto.getUsername());
        return new ResponseEntity<>(new AccessTokenDto(token), HttpStatus.OK);
    }

    @GetMapping("/oauth2/token")
    public ResponseEntity<AccessTokenDto> getToken(@RequestParam("token")AccessTokenDto accessTokenDto){
        if (accessTokenDto.getToken().isEmpty()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(accessTokenDto,HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<AccessTokenDto> register(@Valid @RequestBody RegistrationDto dto) throws Exception {
        log.trace("Sign up request with email {}", dto.getUsername());
        EmailVerificationToken emailVerificationToken = userService.register(dto);
        LOGGER.info("{} registered",dto.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = tokenProvider.generateToken(authentication);
        emailSenderService
                .sendEmail(dto.getUsername(),
                        "Verification email",
                        "http://localhost:8082/auth/verify/email?token="+ emailVerificationToken.getToken()+"&jwt="+jwtToken);
        LOGGER.info("verification email sended to {}",dto.getUsername());
        return new ResponseEntity<>(new AccessTokenDto(jwtToken),HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/verify/email")
    public ResponseEntity<AccessTokenDto> verifyEmail(@RequestParam("token") String token,@RequestParam("jwt") String jwtToken){
        EmailVerificationToken emailVerificationToken = emailVerificationTokenService.findByToken(token);
        String username = emailVerificationToken.getUser().getUsername();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(tokenProvider.validateToken(jwtToken,userDetails)){
            User user = userService.verifyEmail(token);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    "",
                    user.getAuthorities()

            );
            Set<Authority> newAuthorities = updateVerifiedUserRole(authentication);
            user.setAuthorities(newAuthorities);
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                    authentication.getPrincipal(),
                    authentication.getCredentials(),
                    newAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
            String newJwtToken = tokenProvider.generateToken(newAuthentication);
            LOGGER.info("{} email verified",username);
            return new ResponseEntity<>(new AccessTokenDto(newJwtToken),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/verify/number")
    @Transactional
    public ResponseEntity<AccessTokenDto> verifyPhoneNumber(@RequestParam("token") String token,Authentication authentication){
        Set<Authority> newAuthorities = updateVerifiedUserRole(authentication);
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),authentication.getCredentials(),newAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        User user = userService.verifyPhoneNumber(token);
        user.setAuthorities(newAuthorities);
        String jwtToken = tokenProvider.generateToken(newAuthentication);
        return new ResponseEntity<>(new AccessTokenDto(jwtToken),HttpStatus.ACCEPTED);
    }

    @Transactional
    @PostMapping("/sms")
    public ResponseEntity<Void> sendVerificationSms(@Valid @RequestBody SmsRequestDto smsRequestDto,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = tokenProvider.getUsernameFromToken(token);
        Random random = new Random();
        Integer number = random.nextInt(998999)+1000;
        SmsVerificationToken smsVerificationToken = smsVerificationTokenService.save(
                SmsVerificationToken.builder()
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .phoneNumber(smsRequestDto.getPhoneNumber())
                .number(number)
                .user(userService.findByUsername(username))
                .build());
        smsSenderService.sendSms(smsVerificationToken);
        LOGGER.info("Verification sms sended to {}",smsRequestDto.getPhoneNumber());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getAuthenticatedUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    public Set<Authority> updateVerifiedUserRole(Authentication authentication){
        Authority userAuthority = authorityJpaRepository.findByAuthority((UserAuthority.ROLE_USER).toString()).get();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<Authority> newAuthorities = new HashSet<>();
        for(GrantedAuthority authority : authorities){
            if(!(authority.getAuthority().equals((UserAuthority.ROLE_NOT_VERIFIED_USER).toString()))){
                newAuthorities.add(authorityJpaRepository.findByAuthority(authority.getAuthority()).get());
            }
        }
        newAuthorities.add(userAuthority);
        return newAuthorities;
    }
}
