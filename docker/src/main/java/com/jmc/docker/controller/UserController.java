package com.jmc.docker.controller;

import com.jmc.docker.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.jmc.docker.service.UserService;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }
}
