package com.jmc.es.controller;

import com.jmc.es.pojo.User;
import com.jmc.es.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/search/{column}/{like}")
    public List<User> search(@PathVariable String column, @PathVariable String like) {
        return userService.search(column, like);
    }

    @GetMapping("/search")
    public List<User> complexSearch() {
        return userService.complexSearch();
    }
}
