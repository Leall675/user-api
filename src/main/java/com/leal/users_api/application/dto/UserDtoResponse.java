package com.leal.users_api.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDtoResponse {
    private String id;
    private String cpf;
    private String name;
    private String email;
    private String contactNumber;
}
