package com.jmc.dao;

import com.jmc.domain.User;

import java.util.List;

/**
 * @author Jmc
 */
public interface UserDao {
    /**
     * 获取缓存中的所有用户
     * @return 缓存中储存的用户列表，如果redis不存在该键，则返回一个空集合
     */
    List<User> getCacheUserList();

    /**
     * 设置缓存中的用户列表
     * @param userList 用户列表
     */
    void setCacheUserList(List<User> userList);

    /**
     * 获取所有用户
     * @return 用户列表
     */
    List<User> getUserList();

    /**
     * 通过id从数据库获取用户
     * @param id 用户id
     * @return 用户对象
     */
    User getUserById(int id);

    /**
     * 通过用户名从数据库获取用户
     * @param name 用户名
     * @return 用户对象
     */
    User getUserByName(String name);

    /**
     * 添加用户到数据库<br>
     * 由于本项目写操作需求量小，因此采用写串行化缓存一致性解决问题
     * @param u 被添加的用户
     */
    void addUser(User u);

    /**
     * 通过id从数据库中删除用户<br>
     * 由于本项目写操作需求量小，因此采用写串行化缓存一致性解决问题
     * @param id 用户id
     */
    void deleteUserById(int id);

    /**
     * 通过用户名从数据库中删除用户<br>
     * 由于本项目写操作需求量小，因此采用写串行化缓存一致性解决问题
     * @param name 用户名
     */
    void deleteUserByName(String name);

    /**
     * 通过id更新数据库中的用户<br>
     * 由于本项目写操作需求量小，因此采用写串行化缓存一致性解决问题
     * @param u 更新的用户
     */
    void updateUserById(User u);
}
