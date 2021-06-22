package com.jmc.service;

import com.jmc.mapper.StuMapper;
import com.jmc.pojo.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 抽取公共配置
@CacheConfig(cacheNames = "stu")
public class StuServiceImpl implements StuService {
    private StuMapper stuMapper;

    @Autowired
    public void setStuMapper(StuMapper stuMapper) {
        this.stuMapper = stuMapper;
    }

    @Override
    @Cacheable(key = "'list'")
    public List<Stu> getAll() {
        return stuMapper.selectList(null);
    }

    /**
     * @ Cacheable：缓存查询，将方法返回值存到缓存中（只查询一次（如果查询到），之后不执行方法，在缓存中取结果）<br><br>
     * value/cacheNames: 指定缓存名称（CacheManager存放多个Cache）<br><br>
     * key: 缓存数据存放的key值，默认是方法的参数值，也可以用spEL计算<br><br>
     * keyGenerator: key的生成策略，和key进行二选一<br><br>
     * cacheManager: 指定缓存管理器(redis:stu, ehcache:stu)<br><br>
     * cacheResolver: 和cacheManager相同，二选一<br><br>
     * condition: 满足条件才会缓存（在方法被执行之前判断，常用于判断方法参数）<br><br>
     * unless: 满足条件不进行缓存（在方法被执行之后判断，常用于判断#result）<br><br>
     * sync: 是否进行异步缓存（不得与unless连用）<br><br><br>
     *
     * 注意，类中相互调用cache方法是无效的
     */
    @Override
    @Cacheable(key = "#id", condition = "#id > 0", unless = "#result == null")
    public Stu getById(Integer id) {
        return stuMapper.selectById(id);
    }

    /**
     * @ CachePut: 每次都先执行方法，再更新缓存
     */
    @Override
    @CacheEvict(key = "'list'")
    @CachePut(key = "#result.id")
    public Stu update(Stu s) {
        stuMapper.updateById(s);
        return s;
    }

    /**
     * 删除缓存并执行方法（可指定先后顺序）
     */
    @Override
    @Caching(evict = {
            @CacheEvict(key = "'list'"),
            @CacheEvict(key = "#id", beforeInvocation = true)
    })
    public void deleteById(Integer id) {
        stuMapper.deleteById(id);
    }
}
