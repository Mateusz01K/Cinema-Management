package com.example.cinema.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("/getAll")
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ticket/index");
        mav.addObject("ticket", ticketRepository.getAll());
        return mav;
    }
}
