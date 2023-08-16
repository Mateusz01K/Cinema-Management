package com.example.cinema.seance;

import com.example.cinema.cinema.Cinema;
import com.example.cinema.cinema.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@RestController
@RequestMapping("/seance")
public class SeanceController {
    @Autowired
    SeanceRepository seanceRepository;

    @Autowired
    CinemaRepository cinemaRepository;

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

    @GetMapping("/home/add")
    public ModelAndView getAdd(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("seance/add");
        mav.addObject("seance", seanceRepository.getAll());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN)")
    @PostMapping(path = "/add", consumes = "application/x-www-form-urlencoded")
    public RedirectView add(@RequestParam("title") String title, @RequestParam("description") String description){
        Seance newSeance = new Seance(title, description);
        cinemaRepository.incrementSeanceStatistic();
        seanceRepository.save(newSeance);
        return new RedirectView("/seance/home/add");
    }

    @GetMapping("/home/update")
    public ModelAndView getUpdate(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("seance/update");
        mav.addObject("seance", seanceRepository.getAll());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN)")
    @PostMapping(path = "/update", consumes = "application/x-www-form-urlencoded")
    public RedirectView partiallyUpdate(@RequestParam("id") int id,
                                        @RequestParam("title") String title,
                                        @RequestParam("description") String description){
        Seance seanceUpdate = seanceRepository.getById(id);
        if(seanceUpdate != null){
            if(seanceUpdate.getTitle() != null) seanceUpdate.setTitle(title);
            if(seanceUpdate.getDescription() != null) seanceUpdate.setDescription(description);
            seanceRepository.update(seanceUpdate);
            return new RedirectView("/seance/home/update");
        }else{
            return new RedirectView("/seance/home/update");
        }
    }

    @GetMapping("/home/delete")
    public ModelAndView getDelete(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("seance/delete");
        mav.addObject("seance", seanceRepository.getAll());
        return mav;
    }


    @PreAuthorize("hasRole('ADMIN)")
    @PostMapping("/delete/{id}")
    public RedirectView delete(@RequestParam("id") int id){
        cinemaRepository.decrementSeanceStatistic();
        seanceRepository.delete(id);
        return new RedirectView("/seance/home/delete");
    }
}
