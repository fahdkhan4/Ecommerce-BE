package com.example.ecommerce.service;

import com.example.ecommerce.model.Role;
import com.example.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.getAllRoles();
    }

    public void addRoles(){

        List<Role> roles = new ArrayList<>();

        roles.add(new Role("Buyer"));
        roles.add(new Role("Seller"));
        roles.add(new Role("Admin"));

        if(roleRepository.getAllRoles().isEmpty()){
            for(Role role: roles){
                roleRepository.addRoles(role);
            }
        }

    }
}
