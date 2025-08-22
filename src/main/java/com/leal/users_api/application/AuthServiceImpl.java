package com.leal.users_api.application;

import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.domain.User;
import com.leal.users_api.infrastructure.config.Mapper;
import com.leal.users_api.infrastructure.config.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final Mapper mapper;

    @Override
    public String login(UserDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getCpf(), userDto.getPassword())
        );
        User user = mapper.toEntity(userDto);

        return jwtUtil.generateToken(user);
    }
}
