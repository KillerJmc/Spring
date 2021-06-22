package com.jmc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() throws SQLException {
        return "Hello Spring Boot!";
    }
}
