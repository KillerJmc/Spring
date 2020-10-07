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
        var rb = ResourceBundle.getBundle("jdbc");
        var ds = new DruidDataSource();
        ds.setUrl(rb.getString("jdbc.url"));
        ds.setUsername(rb.getString("jdbc.username"));
        ds.setPassword(rb.getString("jdbc.password"));
        jt = new JdbcTemplate(ds);
    }

    @Override
    public void add(User u) {
        jt.update("insert into user values(?, ?, ?, ?)",
                u.getId(),
                u.getName(),
                u.getPassword(),
                u.getGender());
    }

    @Override
    public void delete(User u) {
        jt.update("delete from user where name=? and password=? and gender=?",
                u.getName(),
                u.getPassword(),
                u.getGender());
    }

    @Override
    public boolean deleteById(int id) {
        return jt.update("delete from user where id=?", id) == 1;
    }

    @Override
    public boolean deleteByName(String name) {
        return jt.update("delete from user where name=?", name) == 1;
    }

    @Override
    public boolean update(User u) {
        int result = jt.update("update user set name=?, password=?, gender=? where id=?",
                u.getName(),
                u.getPassword(),
                u.getGender(),
                u.getId());
        // 影响行数为1则修改成功
        return result == 1;
    }

    @Override
    public List<User> getAll() {
        return jt.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getById(int id) {
        return Tries.tryReturnsT(() -> jt.queryForObject("select * from user where id=?",
                new BeanPropertyRowMapper<>(User.class),
                id));
    }

    @Override
    public User getByName(String name) {
        return Tries.tryReturnsT(() -> jt.queryForObject("select * from user where name=?",
                new BeanPropertyRowMapper<>(User.class),
                name));
    }

    @Test
    public void test() {
        System.out.println(jt.update("update user set name=?, password=?, gender=? where id=?",
                "sfl", "342", "lsf", 14));
    }
}
