package com.jmc.service.impl;

import com.jmc.dao.AdminDao;
import com.jmc.dao.UserDao;
import com.jmc.dao.impl.AdminDaoImpl;
import com.jmc.dao.impl.UserDaoImpl;
import com.jmc.domain.Admin;
import com.jmc.domain.User;
import com.jmc.service.AdminService;
import org.junit.Test;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = new AdminDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public String getAllText() {
        var sb = new StringBuilder("\n");
        List<User> l = userDao.getAll();
        String space = "\t".repeat(6);

        sb.append("id").append(space).append("姓名")
          .append(space).append("密码").append(space)
          .append("性别\n\n");
        l.forEach(u -> {
            sb.append(u.getId()).append(space);
            sb.append(u.getName()).append(space);
            sb.append(u.getPassword()).append(space);
            sb.append(u.getGender()).append(space).append("\n\n");
        });
        return sb.toString();
    }

    @Override
    public boolean contains(Admin a) {
        Admin realA = adminDao.getByName(a.getName());
        return realA != null &&
                a.getPassword().equals(realA.getPassword());
    }
}
