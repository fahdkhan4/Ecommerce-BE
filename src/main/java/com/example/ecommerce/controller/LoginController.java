package com.example.ecommerce.controller;

import com.example.ecommerce.model.helper.LoginRequest;
import com.example.ecommerce.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/")
    public boolean loginUser(@RequestBody LoginRequest loginRequest){
        return loginService.loginUser(loginRequest);
    }
}
