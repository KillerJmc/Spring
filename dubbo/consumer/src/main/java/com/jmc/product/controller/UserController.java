package com.jmc.product.controller;

import com.jmc.product.api.pojo.User;
import com.jmc.product.api.service.UserService;
import org.apache.dubbo.common.constants.LoadbalanceRules;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    // 等到调用时候才确定有没有提供者
    @DubboReference(check = false)
    private UserService userService;

    @GetMapping("/get")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/get/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }
}
