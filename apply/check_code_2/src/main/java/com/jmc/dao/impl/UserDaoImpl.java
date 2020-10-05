package com.jmc.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.jmc.dao.UserDao;
import com.jmc.domain.User;
import com.jmc.lang.Tries;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.ResourceBundle;

public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jt;

    public UserDaoImpl() {
        DruidDataSource ds = new DruidDataSource();
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        ds.setUrl(rb.getString("jdbc.url"));
        ds.setUsername(rb.getString("jdbc.username"));
        ds.setPassword(rb.getString("jdbc.password"));
        jt = new JdbcTemplate(ds);
    }

    @Override
    public void insert(User u) {
        jt.update("insert into user(name, password) values(?, ?)", u.getName(), u.getPassword());
    }

    @Override
    public void delete(User u) {
        jt.update("delete from user where name=? and password=?", u.getName(), u.getPassword());
    }

    @Override
    public void deleteById(int id) {
        jt.update("delete from user where id=?", id);
    }

    @Override
    public void update(User u) {
        jt.update("update user set name=?, password=? where id=?",
                u.getName(), u.getPassword(), u.getId());
    }

    @Override
    public List<User> getAll() {
        return jt.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getById(int id) {
        return Tries.tryReturnsT(() -> jt.queryForObject("select * from user where id=?",
                new BeanPropertyRowMapper<>(User.class), id));
    }

    @Override
    public User getByName(String name) {
        return Tries.tryReturnsT(() -> jt.queryForObject("select * from user where name=?",
                new BeanPropertyRowMapper<>(User.class), name));
    }
}
