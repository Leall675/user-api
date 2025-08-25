package com.leal.users_api.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leal.users_api.domain.enuns.RolesEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto {
    @NotEmpty(message = "CPF cannot be empty")
    @Size(min = 11, max = 11, message = "CPF must be exactly 11 digits")
    @Pattern(regexp = "\\d{11}", message = "CPF must contain only digits")
    private String cpf;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}
