package com.leal.users_api.infrastructure;

import com.leal.users_api.domain.User;
import com.leal.users_api.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }
}