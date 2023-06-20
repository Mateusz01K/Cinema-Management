package com.example.cinema.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    CinemaRepository cinemaRepository;

    
    @GetMapping("/getList")
    public List<Cinema> getList(){
        return cinemaRepository.getAll();
    }

    @GetMapping("/home")
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cinema/index");
        mav.addObject("cinema", cinemaRepository.getAll());
        return mav;
    }
}
