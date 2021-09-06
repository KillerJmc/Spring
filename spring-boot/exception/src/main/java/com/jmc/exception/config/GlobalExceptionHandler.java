package com.jmc.exception.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MyException.class)
    public ModelAndView myException(MyException e) {
        return new ModelAndView("myException")
                .addObject("msg", e.getMessage());
    }
}
