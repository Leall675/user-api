package com.leal.users_api.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leal.users_api.domain.enuns.RolesEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @NotEmpty(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números.")
    private String cpf;

    @NotEmpty(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String name;

    @NotEmpty(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail informado não é válido.")
    private String email;

    @NotEmpty(message = "O número de contato é obrigatório.")
    @Pattern(regexp = "\\d{10,11}", message = "O número de contato deve ter 10 ou 11 dígitos numéricos.")
    private String contactNumber;

    @NotEmpty(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String password;
    private List<RolesEnum> roles;

    @Override
    public String toString() {
        return "UserDto{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", roles=" + roles +
                '}';
    }
}
