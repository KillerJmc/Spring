package com.jmc.exception.controller;

import com.jmc.exception.config.MyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/")
    public void createException() {
        throw new MyException("my exception!!!");
    }
}
