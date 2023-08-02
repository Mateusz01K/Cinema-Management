package com.example.cinema.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping
    public ModelAndView registration(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cinema/registration");
        return mav;
    }
}
