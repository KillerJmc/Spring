package com.jmc.product.api.service;

import com.jmc.product.api.pojo.User;

import java.util.List;

/**
 * @author Jmc
 */
public interface UserService {
    List<User> getAll();
    User getById(Integer id);
}
