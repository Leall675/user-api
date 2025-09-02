package com.leal.users_api.domain.port.out;

import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.domain.User;

public interface UserEventPublisher {
    void publishUserCreated(UserDto user);
}
