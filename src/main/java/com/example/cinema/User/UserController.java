package com.example.cinema.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;


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
        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User(userName,hashedPassword,email,"USER");
        userRepository.registerUser(newUser);
        model.addAttribute("messege", "Registration successful.");

        return new RedirectView("/login");
    }


    @GetMapping("/login")
    public ModelAndView getLogin(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cinema/login");
        return mav;
    }

    @PostMapping("/customLogin")
    public RedirectView customLogin(@RequestParam("userName") String userName,
                              @RequestParam("password") String password,
                              Model model){
        User user = userRepository.getUserByName(userName);
        if(user==null || !passwordEncoder.matches(password, user.getPassword())){
            model.addAttribute("error","Invalid username or password.");
            return new RedirectView("/customLogin");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if(role.equals("ADMIN")){
            return new RedirectView("/cinema/home");
        }
        return new RedirectView("/cinema/home");
    }
}
