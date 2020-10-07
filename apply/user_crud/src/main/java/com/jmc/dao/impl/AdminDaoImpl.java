package com.jmc.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.jmc.dao.AdminDao;
import com.jmc.domain.Admin;
import com.jmc.domain.User;
import com.jmc.lang.Tries;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.ResourceBundle;

public class AdminDaoImpl implements AdminDao {
    private final JdbcTemplate jt;

    public AdminDaoImpl() {
        var rb = ResourceBundle.getBundle("jdbc");
        var ds = new DruidDataSource();
        ds.setUrl(rb.getString("jdbc.url"));
        ds.setUsername(rb.getString("jdbc.username"));
        ds.setPassword(rb.getString("jdbc.password"));
        jt = new JdbcTemplate(ds);
    }

    @Override
    public Admin getByName(String name) {
        return Tries.tryReturnsT(() -> jt.queryForObject("select * from admin where name=?",
                new BeanPropertyRowMapper<>(Admin.class),
                name));
    }
}
