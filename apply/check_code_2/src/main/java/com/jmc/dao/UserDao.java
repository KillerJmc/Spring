package com.jmc.dao;

import com.jmc.domain.User;

/**
 * Created by Jmc！！！！！！！
 */
public interface UserDao {
    User getUserByName(String name);
    void insertUser(User u);
    void deleteUser(User u);
}
