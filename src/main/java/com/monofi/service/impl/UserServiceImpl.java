package com.monofi.service.impl;

import com.monofi.auth.mapper.ModelMapperConfig;
import com.monofi.dto.RegistrationDto;
import com.monofi.exception.EmailAlreadyUsedException;
import com.monofi.exception.NotFoundException;
import com.monofi.exception.TokenNotSupportedException;
import com.monofi.model.Authority;
import com.monofi.model.User;
import com.monofi.model.VerificationToken;
import com.monofi.model.enums.UserAuthority;
import com.monofi.repository.AuthorityJpaRepository;
import com.monofi.repository.UserJpaRepository;
import com.monofi.service.UserService;
import com.monofi.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userRepository;
    private final AuthorityJpaRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final VerificationTokenService verificationTokenService;

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
    public VerificationToken register(RegistrationDto dto) {
        userRepository.findByUsername(dto.getUsername())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException(dto.getUsername());
                });
        User user = createUserEntity(dto);
        userRepository.save(user);

        //create and save verification token
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user).build();
        return verificationTokenService.save(verificationToken);
    }


    @Transactional
    @Override
    public User confirm(String token){
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if(verificationToken.getVerifiedAt()!=null){
            throw new TokenNotSupportedException("Token already verified");
        }
        if (verificationToken.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new TokenNotSupportedException("Token expired");
        }
        verificationToken.setVerifiedAt(LocalDateTime.now());
        verificationToken.getUser().setEnabled(true);
        return verificationToken.getUser();
    }

    private User createUserEntity(RegistrationDto dto) {
        User user = modelMapper.map(dto, User.class);
        Authority authority = authorityRepository.findByAuthority(UserAuthority.ROLE_USER.toString()).orElse(
                Authority.builder().authority(UserAuthority.ROLE_USER.toString()).build()
        );
        authorityRepository.save(authority);
        Set<Authority> userAuthority = new HashSet<>();
        userAuthority.add(authority);
        user.setAuthorities(userAuthority);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return user;
    }
}
