package com.example.cinema.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private int id;
    private int seanceCount;
    private int ticketCount;

    public Cinema(int seanceCount, int ticketCount) {
        this.seanceCount = seanceCount;
        this.ticketCount = ticketCount;
    }
}