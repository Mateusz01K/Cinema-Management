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
    private int id_user;
    private float price;
    private String date;


    public Ticket(int id_seance, int id_user, float price, String date) {
        this.id_seance = id_seance;
        this.id_user = id_user;
        this.price = price;
        this.date = date;
    }
}
