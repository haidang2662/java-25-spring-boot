package com.example.demologinweb.service;

import com.example.demologinweb.entity.User;
import com.example.demologinweb.model.LoginRequest;
import com.example.demologinweb.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    public User login(LoginRequest request) {
        return userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
    }

}
