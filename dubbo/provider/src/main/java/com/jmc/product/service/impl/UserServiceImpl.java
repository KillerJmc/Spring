package com.jmc.product.service.impl;


import com.jmc.product.api.pojo.User;
import com.jmc.product.api.service.UserService;
import com.jmc.product.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

@DubboService
@RequiredArgsConstructor
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }
}
