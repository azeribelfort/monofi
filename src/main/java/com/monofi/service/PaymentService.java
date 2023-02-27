package com.monofi.service;

import com.monofi.model.Payment;
import com.monofi.model.User;

import java.util.List;

public interface PaymentService {

    Payment save(Payment payment);

    List<Payment> findAll();

    void deleteById(String id);

    Payment findById(String id);
}
