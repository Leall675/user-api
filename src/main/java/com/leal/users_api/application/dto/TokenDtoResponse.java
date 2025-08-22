package com.leal.users_api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class TokenDtoResponse {
    private String access_token;
    private String token_type;
    private long expires_in;
}
