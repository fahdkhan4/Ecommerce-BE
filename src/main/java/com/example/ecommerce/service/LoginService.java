package com.example.ecommerce.service;


import com.example.ecommerce.model.helper.LoginRequest;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public boolean loginUser(LoginRequest loginRequest){
        if(Objects.isNull(loginRequest.getEmail()) || Objects.isNull(loginRequest.getPassword())){
            System.out.println("Email or password cannot be null");
        }

        return userRepository.authenticateUser(loginRequest);
    }
}
