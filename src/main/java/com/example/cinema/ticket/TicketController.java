package com.example.cinema.ticket;

import com.example.cinema.seance.Seance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("/home/buyTicket")
    public ModelAndView getBuy(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/ticket/buy");
        mav.addObject("ticket", ticketRepository.getAll());
        return mav;
    }

    @PostMapping(path = "/buyTicket", consumes = "application/x-www-form-urlencoded")
    public RedirectView buyTicket(@RequestParam("id_seance") int id_seance,@RequestParam("price") float price, @RequestParam("date") String date){
        Ticket newTicket = new Ticket(id_seance,price, date);
        ticketRepository.buyTicket(newTicket);
        return new RedirectView("/home/buyTicket");
    }
}
