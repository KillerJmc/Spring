package com.jmc.service;

import com.jmc.pojo.User;

/**
 * @author Jmc
 */
public interface UserService {
    /**
     * 通过id获取用户
     * @param id 用户id
     * @return 用户对象
     */
    User getById(int id);

    /**
     * 通过姓名获取用户
     * @param name 姓名
     * @return 用户对象
     */
    User getByName(String name);

    /**
     * 通过姓名和密码获取用户
     * @param u 用户对象
     * @return 结果用户对象
     */
    User getByNameAndPassword(User u);

    /**
     * 插入用户<br>
     * 先更新数据库，后删除缓存，定期删除缓存，保证缓存最终一致性
     * @param u 用户对象
     */
    void insert(User u);

    /**
     * 通过id更新用户<br>
     * 先更新数据库，后删除缓存，定期删除缓存，保证缓存最终一致性
     * @param u 用户对象
     * @return 更新前的用户对象
     */
    User updateById(User u);

    /**
     * 通过id删除用户<br>
     * 先更新数据库，后删除缓存，定期删除缓存，保证缓存最终一致性
     * @param id 用户id
     * @return 被删除的用户对象
     */
    User deleteById(Integer id);

    /**
     * 通过姓名删除用户<br>
     * 先更新数据库，后删除缓存，定期删除缓存，保证缓存最终一致性
     * @param name 用户姓名
     * @return 被删除的用户对象
     */
    User deleteByName(String name);

    /**
     * 获取所有用户的字符串形式
     * @param containsPassword 是否包含密码
     * @return 所有用户的字符串形式
     */
    String getAllUsersAsString(boolean containsPassword);
}
