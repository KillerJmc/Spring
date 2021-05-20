package com.jmc.service.impl;

import com.jmc.dao.AdminDao;
import com.jmc.domain.Admin;
import com.jmc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Admin getAdmin(String name, String password) {
        Admin a = adminDao.getAdminByName(name);
        return a != null && a.getPassword().equals(password) ? a : null;
    }
}
