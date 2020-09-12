package com.jmc.service;

import com.jmc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.sql.JDBCType;

// <bean id="userService" class="com.jmc.service.UserServiceImpl">
//@Component("userService")
@Service("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService {
    //<property name="userDao" ref="userDao"/>
//    @Autowired //按照数据类型从Spring容器中进行匹配的（多个bin不行）
//    @Qualifier("userDao") //是按照id值从容器中进行匹配的，但此处要和@Autowired一起使用
    @Resource(name = "userDao") //相当于@Autowired+@Qualifier
    private UserDao userDao;
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    //注入基本数据类型
    @Value("${jdbc.url}")
    private String url;

    @Override
    public void save() {
        System.out.println(url);
        userDao.save();
    }

    @PostConstruct
    public void init() {
        System.out.println("init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy...");
    }
}
