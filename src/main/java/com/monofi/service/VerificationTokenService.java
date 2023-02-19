package com.monofi.service;

import com.monofi.model.User;
import com.monofi.model.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);

    VerificationToken save(VerificationToken verificationToken);

    List<VerificationToken> findAll();

    VerificationToken findById(Long id);

    void deleteById(Long id);


}
