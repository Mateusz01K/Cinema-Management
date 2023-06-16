package com.example.cinema.seance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/seance")
public class SenaceController {
    @Autowired
    SeanceRepository seanceRepository;


    @GetMapping("/get")
    public ModelAndView getSeance(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("seance", seanceRepository.getAll());
        return mav;
    }
}
