package com.monofi.repository;

import com.monofi.model.SmsVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsVerificationTokenRepository extends JpaRepository<SmsVerificationToken,Long> {
    Optional<SmsVerificationToken> findByNumber(Integer number);
}
