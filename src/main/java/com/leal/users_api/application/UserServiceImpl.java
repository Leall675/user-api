package com.leal.users_api.application;

import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.application.dto.UserDtoResponse;
import com.leal.users_api.domain.User;
import com.leal.users_api.domain.UserRepository;
import com.leal.users_api.infrastructure.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final Mapper mapper;
    private final UserRepository userRepository;

    @Override
    public UserDtoResponse register(UserDto userDto) {
        User user = mapper.toEntity(userDto);
        User userSaved = userRepository.save(user);
        return mapper.toDto(userSaved);
    }

    @Override
    public List<UserDtoResponse> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDtoResponse> responses = new ArrayList<>();

        for (User user : users) {
            responses.add(mapper.toDto(user));
        }
        return responses;
    }
}
