package com.jmc.test;

import com.jmc.mapper.StuAnnoMapper;
import com.jmc.mapper.StuXmlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest {
    @Autowired
    private StuAnnoMapper stuAnnoMapper;

    @Autowired
    private StuXmlMapper stuXmlMapper;

    @Test
    public void testAnnoMapper() {
        var list = stuAnnoMapper.getAll();
        System.out.println(list);
    }

    @Test
    public void testXmlMapper() {
        var list = stuXmlMapper.getAll();
        System.out.println(list);
    }
}
