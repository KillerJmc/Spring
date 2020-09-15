package com.jmc.dao;

public interface AccountDao {
    void in(String inMan, double money);
    void out(String outMan, double money);
}
