package com.example.cinema.seance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public class SeanceRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Seance> getAll(){
        return jdbcTemplate.query("SELECT id, title, description FROM seance",
                BeanPropertyRowMapper.newInstance(Seance.class));
    }

    public Seance getElementById(int id){
        return jdbcTemplate.queryForObject("SELECT id, title, description FROM seance WHERE id=?",
                BeanPropertyRowMapper.newInstance(Seance.class), id);
    }


}




