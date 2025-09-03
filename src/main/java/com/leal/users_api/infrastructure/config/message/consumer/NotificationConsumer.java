package com.leal.users_api.infrastructure.config.message.consumer;

import com.leal.users_api.application.dto.UserDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "user-create-topic", groupId = "notification_group")
    public void listen(UserDto message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
