package com.jmc.dao;

import com.jmc.domain.User;
import com.jmc.jdbc.JDBCTools;
import com.jmc.lang.Tries;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private static final JdbcTemplate jt = JDBCTools.getJt();
    @Override
    public User getUserByName(String username) {
        return Tries.tryReturnsT(() -> jt.queryForObject("select * from user where username=?",
                new BeanPropertyRowMapper<>(User.class), username));
    }

    @Override
    public void insertUser(User u) {
        jt.update("insert into user values(?, ?)", u.getUsername(), u.getPassword());
    }

    @Override
    public void deleteUser(User u) {
        jt.update("delete from user where username=? and password=?", u.getUsername(), u.getPassword());
    }
}
