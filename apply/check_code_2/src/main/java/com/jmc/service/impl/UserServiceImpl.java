package com.jmc.service.impl;

import com.jmc.dao.UserDao;
import com.jmc.dao.impl.UserDaoImpl;
import com.jmc.domain.User;
import com.jmc.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean containsUser(User u) {
        User u0 = new UserDaoImpl().getByName(u.getName());
        return u0 != null && u.getPassword().equals(u0.getPassword());
    }
}
