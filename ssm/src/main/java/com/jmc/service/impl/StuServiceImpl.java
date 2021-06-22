package com.jmc.service.impl;

import com.jmc.domain.Stu;
import com.jmc.mapper.StuMapper;
import com.jmc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService {
    private StuMapper stuMapper;

    @Autowired
    public void setStuMapper(StuMapper stuMapper) {
        this.stuMapper = stuMapper;
    }

    @Override
    public List<Stu> getAll() {
        return stuMapper.getAll();
    }

    @Override
    public void save(Stu s) {
        stuMapper.save(s);
    }
}
