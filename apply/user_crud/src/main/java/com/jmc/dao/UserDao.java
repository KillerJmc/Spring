package com.jmc.dao;

import com.jmc.domain.User;

import java.util.List;

public interface UserDao {
    void add(User u);
    void delete(User u);
    boolean deleteById(int id);
    boolean deleteByName(String name);
    boolean update(User u);
    List<User> getAll();
    User getById(int id);
    User getByName(String name);
}
