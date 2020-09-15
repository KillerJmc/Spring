package com.jmc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private JdbcTemplate j;

    @Override
    public void in(String inMan, double money) {
        j.update("update account set money=money+? where name=?", money, inMan);
    }

    @Override
    public void out(String outMan, double money) {
        j.update("update account set money=money-? where name=?", money, outMan);
    }
}
