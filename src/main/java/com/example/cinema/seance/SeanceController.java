package com.example.cinema.seance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/seance")
public class SeanceController {
    @Autowired
    SeanceRepository seanceRepository;

    @GetMapping("/getList")
    public List<Seance> getList(){
        return seanceRepository.getAll();
    }


    @GetMapping("/home")
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("seance/index");
        mav.addObject("seance", seanceRepository.getAll());
        return mav;
    }
}
