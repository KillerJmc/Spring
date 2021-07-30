package com.jmc.dao;

import com.jmc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        //var userService = (UserService) app.getBean("userService");
        var userService = app.getBean(UserService.class);
        userService.save();

    }
}
