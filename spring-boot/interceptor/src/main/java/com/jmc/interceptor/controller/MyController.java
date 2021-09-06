package com.jmc.interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/login")
    public String login(String password) {
        return "success!";
    }
}
