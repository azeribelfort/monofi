package com.monofi.service;

import com.monofi.model.SmsVerificationToken;

public interface SmsSenderService {
    void sendSms(SmsVerificationToken smsVerificationToken);
}
