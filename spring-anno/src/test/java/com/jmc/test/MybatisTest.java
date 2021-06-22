package com.jmc.test;

import com.jmc.domain.Stu;
import com.jmc.mapper.OrderMapper;
import com.jmc.mapper.StuMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MybatisTest {
    private StuMapper stuMapper;
    private OrderMapper orderMapper;

    @Before
    public void before() throws IOException {
        var config = Resources.getResourceAsStream("mybatis-config.xml");
        var sessionFactory = new SqlSessionFactoryBuilder().build(config);
        var sqlSession = sessionFactory.openSession(true);
        stuMapper = sqlSession.getMapper(StuMapper.class);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void select() {
        System.out.println(stuMapper.getAllStu());
    }

    @Test
    public void insert() {
        stuMapper.saveStu(new Stu("yuu", "234"));
    }

    @Test
    public void update() {
        stuMapper.updateStuById(new Stu(10, "aa", "3", null, null));
    }

    @Test
    public void delete() {
        stuMapper.deleteStuById(10);
    }

    @Test
    public void one2One() {
        orderMapper.getAllWithStu().forEach(System.out::println);
    }

    @Test
    public void one2Many() {
        stuMapper.getAllWithOrders().forEach(System.out::println);
    }

    @Test
    public void many2Many() {
        stuMapper.getAllWithRoles().forEach(System.out::println);
    }
}
