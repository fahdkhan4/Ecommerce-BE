package com.example.ecommerce.repository;

import com.example.ecommerce.model.User;
import com.example.ecommerce.model.helper.LoginRequest;
import com.example.ecommerce.model.helper.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User insertUser(UserDTO user){
         jdbcTemplate.update(
                 "INSERT INTO " +
                         "user(first_name,last_name,password,contact_no,email,dob,created_at,updated_at,status,profile_pic) " +
                         "VALUES(?,?,?,?,?,?,?,?,?,?)",
                 user.getFirstName(),
                 user.getLastName(),
                 user.getPassword(),
                 user.getContact_no(),
                 user.getEmail(),
                 user.getDob(),
                 LocalDate.now(),
                 LocalDate.now(),
                 user.getStatus(),
                 user.getProfilePic()
         );

         return getUserByEmail(user.getEmail());
    }

    public List<User> getAllUsers(){
        return jdbcTemplate.query("SELECT * FROM user", rs -> {
            List<User> userList = new ArrayList<>();

            while(rs.next()){
                userList.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7) != null ? rs.getDate(7).toLocalDate() : null,
                        rs.getDate(8).toLocalDate(),
                        rs.getDate(9).toLocalDate(),
                        rs.getString(10),
                        rs.getString(11)
                ));
            }
            return userList;
        });
    }

    public User getUserByEmail(String email){
        String query = "Select * from user where email=?";
        Object[] arg = {email};
        return jdbcTemplate.query(query,arg,rs->{
            User user = null;
            if(rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7) != null ? rs.getDate(7).toLocalDate() : null,
                        rs.getDate(8).toLocalDate(),
                        rs.getDate(9).toLocalDate(),
                        rs.getString(10),
                        rs.getString(11)
                );
            }
            return user;
        });
    }

    public boolean authenticateUser(LoginRequest loginRequest){
        boolean isUserExist =  jdbcTemplate.query("SELECT * FROM user where email='"+loginRequest.getEmail()+"' LIMIT 1 ",rs->{
            if(
                    rs.next() &&
                            (rs.getString("email").equals(loginRequest.getEmail()) &&
                             rs.getString("password").equals(loginRequest.getPassword()))
            )
            {
                return true;
            }
            return false;
        });
        return isUserExist;
    }

    public User getUserById(int id) {
        String query = "Select * from user where id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg,rs->{
            User user = null;
            if(rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7) != null ? rs.getDate(7).toLocalDate() : null,
                        rs.getDate(8).toLocalDate(),
                        rs.getDate(9).toLocalDate(),
                        rs.getString(10),
                        rs.getString(11)
                );
            }
            return user;
        });
    }
}
