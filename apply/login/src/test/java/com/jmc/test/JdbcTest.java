package com.jmc.test;

import com.jmc.bean.ObjFactory;
import com.jmc.config.SpringConfig;
import com.jmc.domain.User;
import com.jmc.web.IndexServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcTest {
    @Autowired
    private DataSource ds;

    @Autowired
    private JdbcTemplate jt;

    @Test
    public void test() {
//        jt.update("insert into user values(?, ?)", "Jmc", "010809");
//        jt.update("insert into user values(?, ?)", "Lucy", "1987");
    }
}
