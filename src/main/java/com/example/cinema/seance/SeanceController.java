package com.example.cinema.seance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    @PostMapping(path = "/add", consumes = "application/x-www-form-urlencoded")
    public ModelAndView add(@RequestParam("title") String title, @RequestParam("description") String description){
        Seance newSeance = new Seance(title, description);
        seanceRepository.save(newSeance);
        return new ModelAndView("/seance/home");
    }

    @PostMapping("/update")
    public RedirectView partiallyUpdate(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("description") String description){
        Seance seanceUpdate = seanceRepository.getById(id);
        if(seanceUpdate != null){
            if(seanceUpdate.getTitle() != null) seanceUpdate.setTitle(title);
            if(seanceUpdate.getDescription() != null) seanceUpdate.setDescription(description);
            seanceRepository.update(seanceUpdate);
            return new RedirectView("/seance/home");
        }else{
            return new RedirectView("/seance/home");
        }
    }
}
