package com.example.cinema.ticket;

import com.example.cinema.User.UserRepository;
import com.example.cinema.seance.Seance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getList")
    public List<Ticket> getList(){
        return ticketRepository.getAll();
    }

    @GetMapping("/home")
    public ModelAndView get(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        List<Ticket> myTicket = ticketRepository.getMyTicket(userName);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ticket/index");
        mav.addObject("ticket", myTicket);
        return mav;
    }

    @GetMapping("/home/buyTicket")
    public ModelAndView getBuy(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/ticket/buy");
        mav.addObject("ticket", ticketRepository.getAll());
        return mav;
    }

    @PreAuthorize("hasRole('USER)")
    @PostMapping(path = "/buyTicket", consumes = "application/x-www-form-urlencoded")
    public RedirectView buyTicket(@RequestParam("id_seance") int id_seance,
                                  @RequestParam("price") float price,
                                  @RequestParam("date") String date, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        int userId = userRepository.getUserByName(userName).getId();
        Ticket newTicket = new Ticket(id_seance, userId, price, date);
        ticketRepository.buyTicket(newTicket);
        return new RedirectView("/home");
    }
}
