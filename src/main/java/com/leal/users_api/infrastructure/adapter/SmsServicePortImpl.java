package com.leal.users_api.infrastructure.adapter;

import com.leal.users_api.domain.port.out.SmsServicePort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsServicePortImpl implements SmsServicePort {

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
        log.info("Twilio inicializado com sucesso.");
    }

    @Override
    public void sendSms(String to, String text) {

        Message messageSms = Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                text
        ).create();

        log.info("SMS enviado para {}", to);
    }
}
