package com.jmc.service;

import com.jmc.pojo.Stu;

import java.util.List;

public interface StuService {
    List<Stu> getAll();

    Stu getById(Integer id);

    Stu update(Stu s);

    void deleteById(Integer id);
}
