package com.jmc.service;

import com.jmc.domain.Admin;

/**
 * @author Jmc
 */
public interface AdminService {
    /**
     * 返回数据库中符合条件的管理员用户
     * @param name 管理员用户名
     * @param password 密码
     * @return 符合条件的管理员用户
     */
    Admin getAdmin(String name, String password);
}
