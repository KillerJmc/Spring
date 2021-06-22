package com.jmc.mapper;

import com.jmc.domain.Stu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StuMapper {
    @Select("select * from stu")
    List<Stu> getAll();

    @Insert("insert into stu(name, password) values(#{name}, #{password})")
    void save(Stu s);
}
