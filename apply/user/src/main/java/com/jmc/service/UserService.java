package com.jmc.service;

import com.jmc.domain.User;

/**
 * @author Jmc
 */
public interface UserService {
    /**
     * 添加用户
     * @param name 用户名
     * @param age 年龄
     * @param password 密码
     */
    void addUser(String name, int age, String password);

    /**
     * 数据库中是否包含用户
     * @param name 用户名
     * @return 数据库中是否包含该用户
     */
    boolean containsUser(String name);

    /**
     * 返回数据库中符合条件的用户
     * @param name 用户名
     * @param password 密码
     * @return 符合条件的用户
     */
    User getUser(String name, String password);

    /**
     * 在数据库中根据id获取用户
     * @param id 指定的id
     * @return 符合条件的用户
     */
    User getUserById(int id);

    /**
     * 在数据库中根据用户名获取用户
     * @param name 指定的用户名
     * @return 符合条件的用户
     */
    User getUserByName(String name);


    /**
     * 通过id从数据库中删除用户
     * @param id 用户id
     */
    void deleteUserById(int id);

    /**
     * 通过用户名从数据库中删除用户
     * @param name 用户名
     */
    void deleteUserByName(String name);

    /**
     * 通过id更新用户
     * @param id 被更新用户的id
     * @param name 新用户名
     * @param age 新年龄
     * @param password 新密码
     */
    void updateUserById(int id, String name, int age, String password);

    /**
     * 获取所有用户的字符串形式
     * @return 所有用户的字符串形式
     */
    String getAllUsersAsString();

    /**
     * 获取所有用户的字符串形式（不含密码）
     * @return 所有用户的字符串形式（不含密码）
     */
    String getAllUsersWithoutPasswordAsString();
}
