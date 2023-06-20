package com.example.cinema.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private int id;
    private int id_seance;
    private float price;
    private String date;

    public Ticket(int id_seance, float price, String date) {
        this.id_seance = id_seance;
        this.price = price;
        this.date = date;
    }
}
