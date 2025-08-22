package com.leal.users_api.web;

import com.leal.users_api.application.AuthService;
import com.leal.users_api.application.dto.TokenDtoResponse;
import com.leal.users_api.application.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDtoResponse> login(@RequestBody UserDto userDto) {
        TokenDtoResponse tokenDtoResponse = authService.login(userDto);
        return ResponseEntity.ok(tokenDtoResponse);
    }
}
