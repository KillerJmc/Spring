package com.jmc.service;

import com.jmc.dao.UserDao;

public class UserServiceImpl implements UserService {
    UserDao userDao;
    String msg;
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    public UserServiceImpl() {}

    public UserServiceImpl(UserDao userDao, String msg) {
        this.userDao = userDao;
        this.msg = msg;
    }

    @Override
    public void save() {
        userDao.save();
        System.out.println("msg = " + msg);
    }
}
