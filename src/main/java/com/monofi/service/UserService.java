package com.monofi.service;

import com.monofi.dto.AccessTokenDto;
import com.monofi.dto.RegistrationDto;
import com.monofi.model.User;
import com.monofi.model.EmailVerificationToken;

import java.util.List;

public interface UserService {
    User findByUsername(String name);

    User save(User user);

    List<User> findAll();

    User findById(Long userId);

    void deleteById(Long id);

    EmailVerificationToken register(RegistrationDto dto);

    User verifyEmail(String token);

    User verifyPhoneNumber(String token);
}
