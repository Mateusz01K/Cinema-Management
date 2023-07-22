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

    public Seance getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, title, description FROM seance WHERE id=?",
                BeanPropertyRowMapper.newInstance(Seance.class), id);
    }

    public int save(Seance seance){
        return jdbcTemplate.update("INSERT INTO seance(title, description) VALUES (?,?)",
                seance.getTitle(), seance.getDescription());
    }

    public int update(Seance seance){
        return jdbcTemplate.update("UPDATE seance SET title=?, description=? WHERE id=?",
                seance.getTitle(), seance.getDescription(), seance.getId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM seance WHERE id=?", id);
        jdbcTemplate.update("ALTER TABLE seance AUTO_INCREMENT = 1");
    }

}




