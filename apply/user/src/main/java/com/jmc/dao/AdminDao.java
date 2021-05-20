package com.jmc.dao;

import com.jmc.domain.Admin;

import java.util.List;

/**
 * @author Jmc
 */
public interface AdminDao {
    /**
     * 获取缓存中的所有管理员用户
     * @return 缓存中储存的管理员用户列表，如果redis不存在该键，则返回一个空集合
     */
    List<Admin> getCacheAdminList();

    /**
     * 设置缓存中的管理员用户列表
     * @param adminList 管理员用户列表
     */
    void setCacheAdminList(List<Admin> adminList);

    /**
     * 通过管理员用户名从数据库获取管理员用户
     * @param name 管理员用户名
     * @return 管理员用户对象
     */
    Admin getAdminByName(String name);
}
