package com.monofi.service;

import com.monofi.model.EmailVerificationToken;

import java.util.List;

public interface EmailVerificationTokenService {
    EmailVerificationToken findByToken(String token);

    EmailVerificationToken save(EmailVerificationToken emailVerificationToken);

    List<EmailVerificationToken> findAll();

    EmailVerificationToken findById(Long id);

    void deleteById(Long id);


}
