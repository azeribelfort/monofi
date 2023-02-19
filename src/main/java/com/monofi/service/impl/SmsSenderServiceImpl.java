package com.monofi.service.impl;

import com.monofi.config.twilio.TwilioConfiguration;
import com.monofi.config.twilio.TwilioInitializer;
import com.monofi.dto.SmsRequestDto;
import com.monofi.model.SmsVerificationToken;
import com.monofi.service.SmsSenderService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsSenderServiceImpl implements SmsSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
    private final TwilioConfiguration twilioConfiguration;

    @Override
    public void sendSms(SmsVerificationToken smsVerificationToken) {

        MessageCreator messageCreator = Message.creator(
                new PhoneNumber(smsVerificationToken.getPhoneNumber()),
                new PhoneNumber(twilioConfiguration.getTrialNumber()),
                smsVerificationToken.getNumber().toString()
        );
        messageCreator.create();
        LOGGER.info("Message sending to {}",smsVerificationToken.getPhoneNumber());
    }
}
