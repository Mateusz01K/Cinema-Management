package com.example.cinema.seance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance {
    private int id;
    private String title;
    private String description;

    public Seance(String title, String description)
    {
        this.title = title;
        this.description = description;
    }
}
