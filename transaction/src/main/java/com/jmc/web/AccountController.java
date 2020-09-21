package com.jmc.web;

import com.jmc.config.SpringConfig;
import com.jmc.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AccountController {
    public static void main(String[] args) {
        var app = new AnnotationConfigApplicationContext(SpringConfig.class);
//        var app = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = app.getBean(AccountService.class);
        accountService.transfer("Jmc", "Tom", 100);
    }
}
