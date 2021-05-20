package com.jmc.service.impl;

import com.jmc.dao.UserDao;
import com.jmc.domain.User;
import com.jmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jmc
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(String name, int age, String password) {
        userDao.addUser(new User(name, age, password));
    }

    @Override
    public boolean containsUser(String name) {
        User u = userDao.getUserByName(name);
        return u != null;
    }

    @Override
    public User getUser(String name, String password) {
        User u = userDao.getUserByName(name);
        return u != null && u.getPassword().equals(password) ? u : null;
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void deleteUserByName(String name) {
        userDao.deleteUserByName(name);
    }

    @Override
    public void updateUserById(int id, String name, int age, String password) {
        userDao.updateUserById(new User(id, name, age, password));
    }

    @Override
    public String getAllUsersAsString() {
        List<User> users = userDao.getUserList();

        var sb = new StringBuilder("<pre style=\"font-size: 20px\">\n所有用户信息如下：\n\n");
        String blank = "\t\t\t";
        String endLine = "\n\n";

        sb.append("id").append(blank)
                .append("姓名").append(blank)
                .append("年龄").append(blank)
                .append("密码").append(endLine);

        for (var u : users) {
            sb.append(u.getId()).append(blank)
                    .append(u.getName()).append(blank)
                    .append(u.getAge()).append(blank)
                    .append(u.getPassword()).append(endLine);
        }
        sb.append("</pre>");
        return sb.toString();
    }

    @Override
    public String getAllUsersWithoutPasswordAsString() {
        List<User> users = userDao.getUserList();

        var sb = new StringBuilder("<pre style=\"font-size: 20px\">\n所有用户信息如下：\n\n");
        String blank = "\t\t\t";
        String endLine = "\n\n";

        sb.append("id").append(blank)
                .append("姓名").append(blank)
                .append("年龄").append(endLine);

        for (var u : users) {
            sb.append(u.getId()).append(blank)
                    .append(u.getName()).append(blank)
                    .append(u.getAge()).append(endLine);
        }
        sb.append("</pre>");
        return sb.toString();
    }
}
