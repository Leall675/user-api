package com.leal.users_api.infrastructure.config.message.producer;

import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.domain.port.out.UserEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProducerImpl implements UserEventPublisher {

    private final KafkaTemplate<String, UserDto> kafkaTemplate;
    private static final String TOPIC = "user-create-topic";

    @Override
    public void publishUserCreated(UserDto user) {
        kafkaTemplate.send(TOPIC, user);
    }

}
