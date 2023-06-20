package com.example.cinema.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private int id;
    private int name;
    private int seance;
    private int ticket;

    public Cinema(int name, int seance, int ticket){
        this.name = name;
        this.seance = seance;
        this.ticket = ticket;
    }
}