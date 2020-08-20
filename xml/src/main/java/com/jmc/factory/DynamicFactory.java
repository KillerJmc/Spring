package com.jmc.factory;

import com.jmc.dao.UserDao;
import com.jmc.dao.UserDaoImpl;

public class DynamicFactory {
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
