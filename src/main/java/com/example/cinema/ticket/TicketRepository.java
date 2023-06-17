package com.example.cinema.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Ticket> getAll(){
        return jdbcTemplate.query("SELECT id, id_seance, price, date FROM ticket",
                BeanPropertyRowMapper.newInstance(Ticket.class));
    }
}
