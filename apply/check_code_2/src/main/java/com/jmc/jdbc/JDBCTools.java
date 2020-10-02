package com.jmc.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ResourceBundle;

public class JDBCTools {
    private static final JdbcTemplate jt;

    static {
        DruidDataSource ds = new DruidDataSource();
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        ds.setUrl(rb.getString("jdbc.url"));
        ds.setUsername(rb.getString("jdbc.username"));
        ds.setPassword(rb.getString("jdbc.password"));
        jt = new JdbcTemplate(ds);
    }

    public static JdbcTemplate getJt() {
        return jt;
    }
}
