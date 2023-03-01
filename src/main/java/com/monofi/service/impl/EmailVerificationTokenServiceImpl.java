package com.monofi.service.impl;

import com.monofi.exception.NotFoundException;

import com.monofi.model.EmailVerificationToken;
import com.monofi.model.User;
import com.monofi.repository.EmailVerificationTokenRepository;
import com.monofi.service.EmailVerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenServiceImpl implements EmailVerificationTokenService {

    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Override
    public EmailVerificationToken findByToken(String token) {
        return emailVerificationTokenRepository.findByToken(token)
                .orElseThrow(()->new NotFoundException("Can't find token"));
    }

    @Override
    public EmailVerificationToken findByUser(User user) {
        return emailVerificationTokenRepository.findByUser(user)
                .orElseThrow(()->new NotFoundException("Can't find token"));
    }

    @Override
    @Transactional
    public EmailVerificationToken save(EmailVerificationToken emailVerificationToken) {
        return emailVerificationTokenRepository.save(emailVerificationToken);
    }

    @Override
    public List<EmailVerificationToken> findAll() {
        return emailVerificationTokenRepository.findAll();
    }

    @Override
    public EmailVerificationToken findById(Long id) {
        return emailVerificationTokenRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Can't find token with given id"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        emailVerificationTokenRepository.deleteById(id);
    }
}
