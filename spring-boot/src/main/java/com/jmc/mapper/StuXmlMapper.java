package com.jmc.mapper;

import com.jmc.domain.Stu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StuXmlMapper {
    List<Stu> getAll();
}
