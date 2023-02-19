package com.monofi.service;

import com.monofi.model.SmsVerificationToken;

import java.util.List;

public interface SmsVerificationTokenService {

    SmsVerificationToken findByNumber(Integer number);

    SmsVerificationToken save(SmsVerificationToken smsVerificationToken);

    List<SmsVerificationToken> findAll();

    SmsVerificationToken findById(Long id);

    void deleteById(Long id);
}
