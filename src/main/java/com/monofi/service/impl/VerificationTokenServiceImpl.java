package com.monofi.service.impl;

import com.monofi.exception.NotFoundException;

import com.monofi.model.VerificationToken;
import com.monofi.repository.VerificationTokenRepository;
import com.monofi.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token)
                .orElseThrow(()->new NotFoundException("Can't find token"));
    }

    @Override
    @Transactional
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public List<VerificationToken> findAll() {
        return verificationTokenRepository.findAll();
    }

    @Override
    public VerificationToken findById(Long id) {
        return verificationTokenRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Can't find token with given id"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        verificationTokenRepository.deleteById(id);
    }
}
