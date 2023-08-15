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
    private int user_id;
    private float price;
    private String date;


    public Ticket(int id_seance, int user_id, float price, String date) {
        this.id_seance = id_seance;
        this.user_id = user_id;
        this.price = price;
        this.date = date;
    }
}
