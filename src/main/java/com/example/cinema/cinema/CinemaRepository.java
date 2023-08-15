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
        return jdbcTemplate.query("SELECT id, seanceCount, ticketCount FROM cinemaStatistic",
                BeanPropertyRowMapper.newInstance(Cinema.class));
    }

    public void incrementSeanceStatistic(){
        jdbcTemplate.update("UPDATE cinemaStatistic SET seanceCount = seanceCount + 1 WHERE id = 1");
    }

    public void incrementTicketStatistic(){
        jdbcTemplate.update("UPDATE cinemaStatistic SET ticketCount = ticketCount + 1 WHERE id = 1");
    }
}
