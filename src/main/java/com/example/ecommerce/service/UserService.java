package com.example.ecommerce.service;

import com.example.ecommerce.config.JdbcConfig;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User registerUser(User user){
        return userRepository.insertUser(user);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }


    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }


}
