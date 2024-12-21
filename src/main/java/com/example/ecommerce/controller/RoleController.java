package com.example.ecommerce.controller;

import com.example.ecommerce.model.Role;
import com.example.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/get")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

}
