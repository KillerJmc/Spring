package com.jmc.service;

import com.jmc.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    //就近原则
    @Transactional(isolation = Isolation.DEFAULT,
            propagation = Propagation.REQUIRED)
    @Override
    public void transfer(String outMan, String inMan, double money) {
        //开启事务
        accountDao.out(outMan, money);
        int i = 1/0;
        accountDao.in(inMan, money);
        //提交事务
    }
}
