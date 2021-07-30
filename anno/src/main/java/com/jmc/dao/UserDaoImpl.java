package com.jmc.dao;


import org.springframework.stereotype.Repository;

//<bean id="userDao" class="com.jmc.dao.UserDaoImpl"/>
//@Component("userDao")
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save running...");
    }
}
