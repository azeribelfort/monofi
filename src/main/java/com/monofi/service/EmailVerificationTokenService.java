package com.monofi.service;

import com.monofi.model.EmailVerificationToken;
import com.monofi.model.User;

import java.util.List;

public interface EmailVerificationTokenService {
    EmailVerificationToken findByToken(String token);

    EmailVerificationToken save(EmailVerificationToken emailVerificationToken);

    List<EmailVerificationToken> findAll();

    EmailVerificationToken findById(Long id);

    void deleteById(Long id);

    EmailVerificationToken findByUser(User user);

}
