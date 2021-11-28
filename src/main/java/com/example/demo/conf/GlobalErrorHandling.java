package com.example.demo.conf;

import com.example.demo.utils.exeptions.ObjectNotFoundExeption;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalErrorHandling {
    @ExceptionHandler({ObjectNotFoundExeption.class})
    public ModelAndView handleDatabaseErrors(ObjectNotFoundExeption e) {
        ModelAndView modelAndView = new ModelAndView("error/404");


        return modelAndView;
    }
}
