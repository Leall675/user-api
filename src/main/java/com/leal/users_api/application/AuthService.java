package com.leal.users_api.application;

import com.leal.users_api.application.dto.UserDto;

public interface AuthService {
    String login(UserDto userDto);
}
