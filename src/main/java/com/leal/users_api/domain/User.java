package com.leal.users_api.domain;

import com.leal.users_api.domain.enuns.RolesEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String cpf;
    private String name;
    private String email;
    private String contactNumber;
    private String password;
    private List<RolesEnum> roles = new ArrayList<>();
}
