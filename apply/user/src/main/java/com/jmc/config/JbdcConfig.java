package com.jmc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.JedisPool;

import javax.sql.DataSource;

/**
 * @author Jmc
 */
@PropertySource("classpath:jbdc.properties")
public class JbdcConfig {
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean("dataSource")
    public DataSource getDataSource() {
        var dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Autowired
    private DataSource dataSource;

    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        var jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean("jedisPool")
    public JedisPool getJedisPool() {
        return new JedisPool();
    }
}
