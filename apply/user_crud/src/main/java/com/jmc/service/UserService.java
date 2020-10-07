package com.jmc.service;

import com.jmc.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    boolean contains(User u);
    boolean containsName(String name);
    void add(User u);
    String getAllText();
    boolean update(User u);
    boolean deleteById(int id);
    boolean deleteByName(String name);
    User getByName(String name);
}
