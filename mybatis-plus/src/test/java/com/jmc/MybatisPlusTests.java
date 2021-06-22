package com.jmc;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmc.entity.Stu;
import com.jmc.mapper.StuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusTests {
    @Autowired
    private StuMapper stuMapper;

    @Test
    public void insert() {
        var stu = new Stu("刘禅", "666");
        stuMapper.insert(stu);
        // 获得自增长后的id（需要在entity中设置@TableId）
        System.out.println(stu.getId());
    }

    @Test
    public void deleteById() {
        stuMapper.deleteById(11);
    }

    @Test
    public void deleteBatchIds() {
        stuMapper.deleteBatchIds(List.of(23, 25));
    }

    @Test
    public void deleteByMap() {
        // 同时满足条件
        stuMapper.deleteByMap(Map.of("name", "奥", "password", "111"));
    }

    @Test
    public void delete() {
        Stu stu = new Stu("张飞", "666");
        // 同时满足条件
        stuMapper.delete(Wrappers.query(stu));
    }
    
    @Test
    public void updateById() {
        var stu = new Stu("bb", "4");
        stu.setId(11);
        stuMapper.updateById(stu);
    }

    @Test
    public void updateWrapper() {
        stuMapper.update(null, Wrappers.<Stu>lambdaUpdate()
                .set(Stu::getPwd, "3")
                .eq(Stu::getName, "牛逼"));
    }
    
    @Test
    public void selectById() {
        var stu = stuMapper.selectById(3);
        System.out.println(stu);
    }

    @Test
    public void selectBatchIds() {
        stuMapper.selectBatchIds(List.of(1, 2, 100)).forEach(System.out::println);
    }
    
    @Test
    public void selectOne() {
        Stu stu = new Stu("Jmc", null);
        var res = stuMapper.selectOne(Wrappers.query(stu));
        System.out.println(res);
    }

    @Test
    public void selectCount() {
        var count = stuMapper.selectCount(Wrappers.<Stu>lambdaQuery().gt(Stu::getId, 3));
        System.out.println(count);
    }

    @Test
    public void selectList() {
        stuMapper.selectList(Wrappers.<Stu>lambdaQuery().gt(Stu::getId, 3))
                .forEach(System.out::println);
    }

    /**
     * 需要添加分页插件
     */
    @Test
    public void selectPage() {
        var page = stuMapper.selectPage(new Page<>(3, 2), null);
        System.out.println("数据总条数：" + page.getTotal());
        System.out.println("数据总页数：" + page.getPages());
        System.out.println("当前页数：" + page.getCurrent());

        page.getRecords().forEach(System.out::println);
    }


}
