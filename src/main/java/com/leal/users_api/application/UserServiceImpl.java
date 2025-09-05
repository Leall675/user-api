package com.leal.users_api.application;

import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.application.dto.UserDtoResponse;
import com.leal.users_api.application.dto.pagination.PageResponseDto;
import com.leal.users_api.application.validation.UserValidation;
import com.leal.users_api.domain.User;
import com.leal.users_api.domain.UserRepository;
import com.leal.users_api.domain.port.out.UserEventPublisher;
import com.leal.users_api.infrastructure.config.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final Mapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidation userValidation;
    private final UserEventPublisher userEventPublisher;

    @Override
    public UserDtoResponse register(UserDto userDto) {
        User user = mapper.toEntity(userDto);
        userValidation.validateUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSaved = userRepository.save(user);
        userEventPublisher.publishUserCreated(userDto);
        return mapper.toDto(userSaved);
    }

    @Override
    public PageResponseDto<UserDtoResponse> findAll(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);

        List<UserDtoResponse> content = page.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        return new PageResponseDto<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }
}
