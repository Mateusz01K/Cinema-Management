package com.example.cinema.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int register(User user){
        return jdbcTemplate.update("INSERT INTO user(userName, password, email, role) VALUES (?,?,?,?)",
                user.getUserName(),user.getPassword(),user.getEmail(),user.getRole());
    }

    public int login(User user){
        return jdbcTemplate.update("SELECT id, userName, password, email, role FROM user",
                user.getId(),user.getUserName(),user.getPassword(),user.getEmail(),user.getRole());
    }
}
