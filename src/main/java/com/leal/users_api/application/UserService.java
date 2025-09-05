package com.leal.users_api.application;

import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.application.dto.UserDtoResponse;
import com.leal.users_api.application.dto.pagination.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDtoResponse register(UserDto userDto);
    PageResponseDto<UserDtoResponse> findAll(Pageable pageable);
}
