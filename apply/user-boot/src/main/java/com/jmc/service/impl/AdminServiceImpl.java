package com.jmc.service.impl;

import com.jmc.config.exception.MsgException;
import com.jmc.pojo.Admin;
import com.jmc.service.AdminService;
import com.jmc.mapper.AdminMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jmc
 */
@Service
public class AdminServiceImpl implements AdminService {
    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }


    @Override
    public Admin getByNameAndPassword(String name, String password) {
        if (name.isBlank()) {
            throw new MsgException("用户名为空");
        } else if (password.isBlank()) {
            throw new MsgException("密码为空");
        }

        Admin a = new Admin();
        a.setName(name);
        a.setPassword(password);
        return adminMapper.getByNameAndPassword(a);
    }
}
