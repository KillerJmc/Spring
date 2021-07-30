package com.jmc.service.impl;

import com.jmc.config.exception.MsgException;
import com.jmc.mapper.AdminMapper;
import com.jmc.pojo.Admin;
import com.jmc.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    @Override
    public Admin getByNameAndPassword(String name, String password) {
        if (name.isBlank()) {
            throw new MsgException("用户名为空");
        } else if (password.isBlank()) {
            throw new MsgException("密码为空");
        }

        var res = adminMapper.selectByMap(Map.of("name", name, "password", password));
        return res.isEmpty() ? null : res.get(0);
    }
}
