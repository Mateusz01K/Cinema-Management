package com.example.cinema.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int registerUser(User user){
        return jdbcTemplate.update("INSERT INTO accont(userName, password, email, role) VALUES (?,?,?,?)",
                user.getUserName(),user.getPassword(),user.getEmail(),user.getRole());
    }

    public User getUserByName(String userName){
        return jdbcTemplate.queryForObject("SELECT id, userName, password, email, role FROM accont WHERE userName=?",
                BeanPropertyRowMapper.newInstance(User.class), userName);
    }
}
