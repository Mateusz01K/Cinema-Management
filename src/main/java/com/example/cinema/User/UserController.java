package com.example.cinema.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cinema/registration");
        return mav;
    }


    @PostMapping("/registration")
    public RedirectView registerUser(@RequestParam("userName") String userName,
                                     @RequestParam("password") String password,
                                     @RequestParam("email") String email,
                                     Model model){
        /*
        if(userRepository.getUserByName(userName)==null){
            model.addAttribute("error","Username already exists.");
        }

         */
        User newUser = new User(userName,password,email,"USER");
        userRepository.registerUser(newUser);
        model.addAttribute("messege", "Registration successful.");

        return new RedirectView("/login");
    }

    /*
    @GetMapping("/login")
    public ModelAndView getLogin(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cinema/home");
        return mav;
    }

     */

    @PostMapping("/login")
    public RedirectView login(@RequestParam("userName") String userName,
                              @RequestParam("password") String password,
                              Model model){
        User user = userRepository.getUserByName(userName);
        if(user==null || !user.getPassword().equals(password)){
            model.addAttribute("error","Invalid username or password.");
        }
        return new RedirectView("/cinema/home");
    }
}
