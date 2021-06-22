package com.jmc.mapper;

import com.jmc.domain.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StuAnnoMapper {
    @Select("select * from stu")
    List<Stu> getAll();
}
