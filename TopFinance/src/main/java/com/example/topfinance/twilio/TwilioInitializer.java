package com.example.topfinance.twilio;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);

    private final TwilioConfiguration twilioConfiguration;

   // private final static String ACCOUNT_SID= "AC4b67c487afdfcc9aaaf8e0000c2a9c9f";
   // private final static String AUTH_TOKEN="6a606c6627bd32a64d74ecdeb932140a";

    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );

       // Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        //LOGGER.info("Twilio initialized ... with account sid {} ", ACCOUNT_SID);
        LOGGER.info("Twilio initialized ... with account sid {} ", twilioConfiguration.getAccountSid());
    }
}
