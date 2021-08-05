package com.jmc.docker.service;

import com.jmc.docker.pojo.User;

/**
 * @author Jmc
 */
public interface UserService {
    User getById(Integer id);
}
