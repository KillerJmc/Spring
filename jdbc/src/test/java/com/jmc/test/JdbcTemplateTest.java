package com.jmc.test;

import com.jmc.config.SpringConfig;
import com.jmc.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate j;

    @Test
    public void addOrUpdate() {
        j.update("insert into account values(?, ?)", "Jmc", 15000);
    }

    @Test
    public void delete() {
        j.update("delete from account where name=?", "Jmc");
    }

    @Test
    public void queryAll() {
        List<Account> result = j.query("select * from account",
                new BeanPropertyRowMapper<>(Account.class));
        System.out.println(result);
    }

    @Test
    public void queryOne() {
        Account jmc = j.queryForObject("select * from account where name=?",
                new BeanPropertyRowMapper<>(Account.class),
                "Jmc");
        System.out.println(jmc);
    }
    
    @Test
    public void queryCount() {
        Long count = j.queryForObject("select count(*) from account", Long.class);
        System.out.println(count);
    }
}
