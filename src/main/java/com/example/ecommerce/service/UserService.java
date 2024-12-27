package com.example.ecommerce.service;

import com.example.ecommerce.config.JdbcConfig;
import com.example.ecommerce.model.Location;
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

    @Autowired
    LocationService locationService;


    public UserDTO registerUser(UserDTO userDTO){

        Location location = locationService.addLocation(userDTO.getLocation());

        userDTO.getLocation().setId(location.getId());

        User user =  userRepository.insertUser(userDTO);

        roleService.addUserRoles(user.getId(),userDTO.getRole());

        userDTO.setId(user.getId());
        userDTO.setLocation(location);

        return userDTO;
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }


    public UserDTO getUserByEmail(String email){
        User user =  userRepository.getUserByEmail(email);

        List<Role> roles = roleService.getUserRoles(user.getId());
        Location location = locationService.getLocationById(user.getLocationId());

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
                roles,
                location
        );

        return userDTO;
    }

    public UserDTO getUserById(int id){
        User user =  userRepository.getUserById(id);

        List<Role> roles = roleService.getUserRoles(user.getId());
        Location location = locationService.getLocationById(user.getLocationId());

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
                roles,
                location
        );

        return userDTO;
    }

}
