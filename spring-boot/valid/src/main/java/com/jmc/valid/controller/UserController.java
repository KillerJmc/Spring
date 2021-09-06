package com.jmc.valid.controller;

import com.jmc.valid.pojo.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @GetMapping("/login")
    public User login(@Validated User u) {
        return u;
    }
}
