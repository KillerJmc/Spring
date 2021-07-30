package com.jmc.web;

import com.jmc.config.SpringConfiguration;
import com.jmc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringJunitTest {
    @Autowired
    private UserService userService;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Test
    public void test1() {
        userService.save();
    }

    @Test
    public void test2() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
