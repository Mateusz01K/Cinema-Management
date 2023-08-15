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
        return jdbcTemplate.query("SELECT id, id_seance, id_user, price, date FROM ticket",
                BeanPropertyRowMapper.newInstance(Ticket.class));
    }

    public List<Ticket> getMyTicket(String userName){
        return jdbcTemplate.query("SELECT id, id_seance, id_user, price, date FROM ticket " +
                        "WHERE id_user=(SELECT id FROM account WHERE userName=?)",
                BeanPropertyRowMapper.newInstance(Ticket.class), userName);
    }

    public int buyTicket(Ticket ticket){
        return jdbcTemplate.update("INSERT INTO ticket(id_seance, id_user, price, date) VALUES (?,?,?,?)",
        ticket.getId_seance(), ticket.getUser_id(),ticket.getPrice(), ticket.getDate());
    }
}
