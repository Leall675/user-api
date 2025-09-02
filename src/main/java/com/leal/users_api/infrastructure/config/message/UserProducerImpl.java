package com.leal.users_api.infrastructure.config.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.domain.User;
import com.leal.users_api.domain.port.out.UserEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
