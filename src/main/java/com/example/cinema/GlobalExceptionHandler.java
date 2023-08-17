package com.example.cinema;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ModelAndView handlerException(Exception ex){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cinema/index");
        return mav;
    }
}
