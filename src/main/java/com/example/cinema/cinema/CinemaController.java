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

    @GetMapping("/home")
    public ModelAndView getIndex(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cinema/index");
        return mav;
    }

    @GetMapping("/seance")
    public ModelAndView getSeance(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/seance/index");
        return mav;
    }

    @GetMapping("/ticket")
    public ModelAndView getTicket(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/ticket/index");
        return mav;
    }

    @GetMapping("/getList")
    public List<Cinema> getList(){
        return cinemaRepository.getAll();
    }

    @GetMapping("/getAll")
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cinema/index");
        mav.addObject("cinema", cinemaRepository.getAll());
        return mav;
    }
}
