package com.leal.users_api.application.validation;

import com.leal.users_api.application.dto.UserDto;
import com.leal.users_api.domain.User;
import com.leal.users_api.domain.UserRepository;
import com.leal.users_api.domain.enuns.RolesEnum;
import com.leal.users_api.infrastructure.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserValidation {

    @Autowired
    private UserRepository userRepository;

    public void validateUser(UserDto userDto) {
        Optional<User> user = userRepository.findByCpf(userDto.getCpf());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("Usuário com o CPF informado já existe na base de dados.");
        }
        rolesDefinition(userDto);
    }

    private void rolesDefinition(UserDto userDto) {
        if (userDto.getRoles() == null){
            userDto.setRoles(Collections.singletonList(RolesEnum.COMMON));
        }
    }
}
