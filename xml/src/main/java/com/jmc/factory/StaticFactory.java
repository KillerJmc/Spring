package com.jmc.factory;

import com.jmc.dao.UserDao;
import com.jmc.dao.UserDaoImpl;

public class StaticFactory {
    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
