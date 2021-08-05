package com.jmc.docker.service.impl;

import com.jmc.docker.pojo.User;
import com.jmc.docker.service.UserService;
import com.jmc.docker.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }
}
