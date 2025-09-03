package com.leal.users_api.domain.port.out;

public interface SmsServicePort {
    void sendSms(String to, String message);
}
