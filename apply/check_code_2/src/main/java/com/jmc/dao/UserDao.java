package com.jmc.dao;

import com.jmc.domain.User;

import java.util.List;

/**
 * Created by Jmc！！！！！！！
 */
public interface UserDao {
    void insert(User u);
    void delete(User u);
    void deleteById(int id);
    void update(User u);
    List<User> getAll();
    User getById(int id);
    User getByName(String name);
}
