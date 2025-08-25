package com.leal.users_api.application;

import com.leal.users_api.application.dto.LoginDto;
import com.leal.users_api.application.dto.TokenDtoResponse;
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

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final Mapper mapper;

    @Override
    public TokenDtoResponse login(LoginDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getCpf(), userDto.getPassword())
        );
        User user = mapper.toLogin(userDto);
        String token = jwtUtil.generateToken(user);
        Instant expiresAt = jwtUtil.generateExpirationDate();
        long expiresIn = ChronoUnit.SECONDS.between(Instant.now(), expiresAt);
        return new TokenDtoResponse(token, "Bearer", expiresIn);
    }
}
