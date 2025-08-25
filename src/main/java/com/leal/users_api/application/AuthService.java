package com.leal.users_api.application;

import com.leal.users_api.application.dto.LoginDto;
import com.leal.users_api.application.dto.TokenDtoResponse;
import com.leal.users_api.application.dto.UserDto;

public interface AuthService {
    TokenDtoResponse login(LoginDto userDto);
}
