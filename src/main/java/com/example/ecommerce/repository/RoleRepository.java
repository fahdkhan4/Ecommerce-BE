package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addRoles(Role role){
        jdbcTemplate.update(
                "INSERT INTO role (name) " +
                        "VALUES (?)",
                role.getName()
        );

    }

    public List<Role> getAllRoles(){
        return jdbcTemplate.query("SELECT * FROM role", rs -> {
            List<Role> roles = new ArrayList<>();

            while (rs.next()) {
                roles.add(new Role(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
            return roles;
        });
    }

    public void addUserRole(int userId,int roleId){
        jdbcTemplate.update(
                "INSERT INTO user_role (user_id,role_id) " +
                        "VALUES (?,?)",
                userId,
                roleId
        );

    }

}
