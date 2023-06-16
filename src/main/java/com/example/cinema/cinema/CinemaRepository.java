package com.example.cinema.cinema;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CinemaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Cinema> getAll(){
        return jdbcTemplate.query("SELECT id, name, seance, ticket FROM cinemaStatistic",
                BeanPropertyRowMapper.newInstance(Cinema.class));
    }
}
