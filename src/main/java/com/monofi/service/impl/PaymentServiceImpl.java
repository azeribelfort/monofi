package com.monofi.service.impl;

import com.monofi.exception.NotFoundException;
import com.monofi.model.Payment;
import com.monofi.model.User;
import com.monofi.repository.PaymentJpaRepository;
import com.monofi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    @Transactional
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(payment);
    }

    @Override
    public List<Payment> findAll() {
        return paymentJpaRepository.findAll();
    }

    @Override
    public Payment findById(String id) {
        return paymentJpaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Can't find payment with given id"));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        paymentJpaRepository.deleteById(id);
    }
}
