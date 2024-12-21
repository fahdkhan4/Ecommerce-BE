package com.example.ecommerce.service;

import com.example.ecommerce.config.JdbcConfig;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.helper.UserDTO;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;


    public UserDTO registerUser(UserDTO userDTO){
        User user =  userRepository.insertUser(userDTO);

        roleService.addUserRoles(user.getId(),userDTO.getRole());

        userDTO.setId(user.getId());

        return userDTO;
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }


    public UserDTO getUserByEmail(String email){
        User user =  userRepository.getUserByEmail(email);

        List<Role> roles = roleService.getUserRoles(user.getId());

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getContact_no(),
                user.getEmail(),
                user.getDob(),
                user.getCreatedDate(),
                user.getUpdatedDate(),
                user.getStatus(),
                user.getProfilePic(),
                roles
        );

        return userDTO;
    }


}
