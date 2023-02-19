package com.monofi.service;

import com.monofi.dto.RegistrationDto;
import com.monofi.model.User;
import com.monofi.model.VerificationToken;

import java.util.List;

public interface UserService {
    User findByUsername(String name);

    User save(User user);

    List<User> findAll();

    User findById(Long userId);

    void deleteById(Long id);

    VerificationToken register(RegistrationDto dto);

    User confirm(String token);
}
