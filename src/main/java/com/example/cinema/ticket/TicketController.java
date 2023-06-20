package com.example.cinema.ticket;

import com.example.cinema.seance.Seance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("/getList")
    public List<Ticket> getList(){
        return ticketRepository.getAll();
    }

    @GetMapping("/home")
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ticket/index");
        mav.addObject("ticket", ticketRepository.getAll());
        return mav;
    }
}
