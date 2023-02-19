package com.monofi.config.twilio;

import com.twilio.Twilio;
import jdk.nashorn.internal.runtime.logging.Loggable;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {
    private final TwilioConfiguration twilioConfiguration;

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration){
        this.twilioConfiguration=twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
        LOGGER.info("Twilio initialized with account sid {}",twilioConfiguration.getAccountSid());
    }
}
