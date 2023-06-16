package com.example.cinema.cinema;

import lombok.Data;

@Data
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