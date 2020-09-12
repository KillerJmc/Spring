package com.jmc.web;

import com.jmc.config.SpringConfiguration;
import com.jmc.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    public static void main(String[] args) {
//        var app = new ClassPathXmlApplicationContext("applicationContext.xml");
        var app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        var userService = app.getBean(UserService.class);
        userService.save();
        app.close();
    }
}
