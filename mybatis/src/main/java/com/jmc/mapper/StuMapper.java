package com.jmc.mapper;

import com.jmc.domain.Stu;

import java.util.List;

public interface StuMapper {
    /**
     * 一对多
     */
    List<Stu> getAllWithOrders();

    /**
     * 多对多
     */
    List<Stu> getAllWithRoles();

    /**
     * 任意条件查找用户
     */
    List<Stu> findStu(Stu s);

    /**
     * 寻找多个id的用户
     */
    List<Stu> getStuByIds(List<Integer> ids);

    List<Stu> getAllStu();
    void saveStu(Stu s);
    void updateStuById(Stu s);
    void deleteStuById(int id);
}
