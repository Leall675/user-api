package com.leal.users_api.application;

import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.application.dto.UserDtoResponse;

import java.util.List;

public interface UserService {
    UserDtoResponse register(UserDto userDto);
    List<UserDtoResponse> findAll();
}
