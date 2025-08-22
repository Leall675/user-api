package com.leal.users_api.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.application.dto.UserDtoResponse;
import com.leal.users_api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Autowired
    private ObjectMapper objectMapper;

    public User toEntity(UserDto userDto) {
        return objectMapper.convertValue(userDto, User.class);
    }

    public UserDtoResponse toDto(User user) {
        return objectMapper.convertValue(user, UserDtoResponse.class);
    }

}
