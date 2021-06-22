package com.jmc.service;

import com.jmc.domain.Stu;

import java.util.List;

public interface StuService {
    List<Stu> getAll();
    void save(Stu s);
}
