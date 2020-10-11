package com.jmc.service.impl;

import com.jmc.dao.AdminDao;
import com.jmc.dao.impl.AdminDaoImpl;
import com.jmc.domain.Admin;
import com.jmc.service.AdminService;


public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = new AdminDaoImpl();

    @Override
    public boolean contains(Admin a) {
        Admin realA = adminDao.getByName(a.getName());
        return realA != null &&
                a.getPassword().equals(realA.getPassword());
    }
}
