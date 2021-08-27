package com.jmc.es.service;

import com.jmc.es.pojo.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    List<User> search(String field, String value);

    List<User> complexSearch();
}
