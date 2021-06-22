package com.jmc.service;

import com.jmc.pojo.Admin;

/**
 * @author Jmc
 */
public interface AdminService {
    /**
     * 通过姓名和密码获取管理员用户
     * @param name 姓名
     * @param password 密码
     * @return 管理员用户对象
     */
    Admin getByNameAndPassword(String name, String password);
}
