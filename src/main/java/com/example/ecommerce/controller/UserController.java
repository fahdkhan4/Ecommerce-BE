package com.example.ecommerce.controller;

import com.example.ecommerce.model.User;
import com.example.ecommerce.model.helper.UserDTO;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public UserDTO registerUser(@RequestBody UserDTO user){
        if(Objects.isNull(user)){
            System.out.println("Issue while registering user details Cannot be null!");
        }
        return userService.registerUser(user);
    }

    @GetMapping("/get")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get/{email}")
    public UserDTO getUserByEmail(@PathVariable String email){
        if(email == null){
            System.out.println("Invalid email cannot be null");
        }
        return userService.getUserByEmail(email);
    }

//    @DeleteMapping("/delete")
//    public void deleteUserById(@PathVariable Integer userId){
//        if(Objects.isNull(userId)){
//            System.out.println("Issue while deleting user details user id Cannot be null!");
//        }
//
//        userService.deleteUser();
//    }





}
