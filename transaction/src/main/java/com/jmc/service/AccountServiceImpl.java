package com.jmc.service;

import com.jmc.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public void transfer(String outMan, String inMan, double money) {
        //开启事务
        accountDao.out(outMan, money);
        int i = 1/0;
        accountDao.in(inMan, money);
        //提交事务
    }
}
