package com.jmc.dao;

import com.jmc.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserDaoImpl implements UserDao {
//    public UserDaoImpl() {
//        System.out.println("UserDaoImpl创建...");
//    }

//    public void init() {
//        System.out.println("初始化方法...");
//    }

//    public void destroy() {
//        System.out.println("销毁方法...");
//    }

    private String username;
    private int age;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private List<String> strList;
    private Map<String, User> userMap;
    private Properties properties;

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "UserDaoImpl{\n" +
                "\tusername='" + username + '\'' +
                ",\n\tage=" + age +
                ",\n\tstrList=" + strList +
                ",\n\tuserMap=" + userMap +
                ",\n\tproperties=" + properties +
                "\n}";
    }

    public void save() {
        System.out.println(this.toString());
        System.out.println("save running...");
    }
}
