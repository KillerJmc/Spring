package com.jmc.service.impl;

import com.jmc.dao.UserDao;
import com.jmc.dao.impl.UserDaoImpl;
import com.jmc.domain.User;
import com.jmc.service.UserService;
import org.junit.Test;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public boolean contains(User u) {
        User realU = userDao.getByName(u.getName());
        return realU != null &&
            u.getPassword().equals(realU.getPassword());
    }

    @Override
    public boolean containsName(String name) {
        return userDao.getByName(name) != null;
    }

    @Override
    public void add(User u) {
        userDao.add(u);
    }

    @Override
    public String getAllText() {
        var sb = new StringBuilder("\n");
        List<User> l = getAll();
        String space = "\t".repeat(10);
        
        sb.append("id").append(space).append("姓名").append(space).append("性别\n\n");
        l.forEach(u -> {
            sb.append(u.getId()).append(space);
            sb.append(u.getName()).append(space);
            sb.append(u.getGender()).append(space).append("\n\n");
        });

        return sb.toString();
    }

    @Override
    public boolean update(User u) {
        return userDao.update(u);
    }

    @Override
    public boolean deleteById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public boolean deleteByName(String name) {
        return userDao.deleteByName(name);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }
}
