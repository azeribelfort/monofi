package com.monofi.service.impl;

import com.monofi.auth.TokenProvider;
import com.monofi.controller.AuthController;
import com.monofi.dto.AccessTokenDto;
import com.monofi.dto.RegistrationDto;
import com.monofi.exception.EmailAlreadyUsedException;
import com.monofi.exception.NotFoundException;
import com.monofi.exception.TokenNotSupportedException;
import com.monofi.model.Authority;
import com.monofi.model.SmsVerificationToken;
import com.monofi.model.User;
import com.monofi.model.EmailVerificationToken;
import com.monofi.model.enums.UserAuthority;
import com.monofi.repository.AuthorityJpaRepository;
import com.monofi.repository.EmailVerificationTokenRepository;
import com.monofi.repository.UserJpaRepository;
import com.monofi.service.SmsVerificationTokenService;
import com.monofi.service.UserService;
import com.monofi.service.EmailVerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserJpaRepository userRepository;
    private final AuthorityJpaRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final TokenProvider tokenProvider;
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final SmsVerificationTokenService smsVerificationTokenService;
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException("Can't find user with given username"));
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException("Can't find user with given id"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public EmailVerificationToken register(RegistrationDto dto) {
        userRepository.findByUsername(dto.getUsername())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException(dto.getUsername());
                });
        User user = createUserEntity(dto);
        userRepository.save(user);

        //create and save verification token
        String token = UUID.randomUUID().toString();
        EmailVerificationToken emailVerificationToken = EmailVerificationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .user(user).build();
        return emailVerificationTokenService.save(emailVerificationToken);
    }


    @Transactional
    @Override
    public User verifyEmail(String token){
        EmailVerificationToken emailVerificationToken = emailVerificationTokenService.findByToken(token);
        if(emailVerificationToken.getVerifiedAt()!=null){
            throw new TokenNotSupportedException("Token already verified");
        }
        emailVerificationToken.setVerifiedAt(LocalDateTime.now());
        return emailVerificationToken.getUser();
    }

    @Transactional
    @Override
    public User verifyPhoneNumber(String token){
        SmsVerificationToken smsVerificationToken = smsVerificationTokenService.findByNumber(new Integer(token));
        if(smsVerificationToken.getVerifiedAt()!=null){
            throw new TokenNotSupportedException("Phone number already verified");
        }
        if (smsVerificationToken.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new TokenNotSupportedException("Token expired");
        }
        smsVerificationToken.setVerifiedAt(LocalDateTime.now());
        EmailVerificationToken emailVerificationToken = emailVerificationTokenRepository.findByUser(smsVerificationToken.getUser())
                .orElseThrow(()->new NotFoundException("Can't find token"));
        if (emailVerificationToken.getVerifiedAt()==null){
            throw new IllegalStateException("Email not verified");
        }
        User user = smsVerificationToken.getUser();
        LOGGER.info("{} verified",smsVerificationToken.getPhoneNumber());
        return user;
    }

    private User createUserEntity(RegistrationDto dto) {
        User user = modelMapper.map(dto, User.class);
        Authority authority = authorityRepository.findByAuthority(UserAuthority.ROLE_NOT_VERIFIED_USER.toString()).orElse(
                Authority.builder().authority(UserAuthority.ROLE_NOT_VERIFIED_USER.toString()).build()
        );
        authorityRepository.save(authority);
        Set<Authority> userAuthority = new HashSet<>();
        userAuthority.add(authority);
        user.setAuthorities(userAuthority);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return user;
    }
}
