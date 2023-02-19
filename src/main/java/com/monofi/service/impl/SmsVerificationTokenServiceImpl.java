package com.monofi.service.impl;

import com.monofi.exception.NotFoundException;
import com.monofi.model.SmsVerificationToken;
import com.monofi.repository.SmsVerificationTokenRepository;
import com.monofi.service.SmsVerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SmsVerificationTokenServiceImpl implements SmsVerificationTokenService {
    private final SmsVerificationTokenRepository smsVerificationTokenRepository;

    @Override
    public SmsVerificationToken findByNumber(Integer number) {
        return smsVerificationTokenRepository.findByNumber(number)
                .orElseThrow(()->new NotFoundException("Can't find token"));
    }

    @Override
    @Transactional
    public SmsVerificationToken save(SmsVerificationToken smsVerificationToken) {
        return smsVerificationTokenRepository.save(smsVerificationToken);
    }

    @Override
    public List<SmsVerificationToken> findAll() {
        return smsVerificationTokenRepository.findAll();
    }

    @Override
    public SmsVerificationToken findById(Long id) {
        return smsVerificationTokenRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Can't find token with given id"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        smsVerificationTokenRepository.deleteById(id);
    }
}
