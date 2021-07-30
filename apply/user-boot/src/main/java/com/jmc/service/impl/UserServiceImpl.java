package com.jmc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jmc.common.Const;
import com.jmc.config.exception.MsgException;
import com.jmc.mapper.UserMapper;
import com.jmc.pojo.User;
import com.jmc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = Const.USER_CACHE)
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public User getById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    @Cacheable(key = "#name", unless = "#result == null")
    public User getByName(String name) {
        var res = userMapper.selectByMap(Map.of("name", name));
        return res.isEmpty() ? null : res.get(0);
    }

    @Override
    @Cacheable(key = "#u.name", unless = "#result == null")
    public User getByNameAndPassword(User u) {
        return userMapper.selectOne(new QueryWrapper<>(u));
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#u.id"), @CacheEvict(key = "#u.name"),
            @CacheEvict(key = "'list-true'"), @CacheEvict(key = "'list-false'")
    })
    public void insert(User u) {
        if (getByName(u.getName()) != null) {
            throw new MsgException("注册失败，用户名已存在！");
        }

        userMapper.insert(u);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#result.id"), @CacheEvict(key = "#result.name"),
            @CacheEvict(key = "'list-true'"), @CacheEvict(key = "'list-false'")
    })
    public User updateById(User u) {
        User updatedUser = getById(u.getId());

        if (updatedUser == null) {
            throw new MsgException("修改失败！被修改的用户不存在");
        }

        if (!updatedUser.getName().equals(u.getName()) && getByName(u.getName()) != null) {
            throw new MsgException("用户名已存在，修改失败");
        }

        userMapper.updateById(u);
        return updatedUser;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#result.id"), @CacheEvict(key = "#result.name"),
            @CacheEvict(key = "'list-true'"), @CacheEvict(key = "'list-false'")
    })
    public User deleteById(Integer id) {
        if (id == null) {
            throw new MsgException("删除的id不能为空");
        }

        User deletedUser = getById(id);
        if (deletedUser == null) {
            throw new MsgException("删除的id不存在");
        }

        userMapper.deleteById(id);
        return deletedUser;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#result.id"), @CacheEvict(key = "#result.name"),
            @CacheEvict(key = "'list-true'"), @CacheEvict(key = "'list-false'")
    })
    public User deleteByName(String name) {
        if (name.isBlank()) {
            throw new MsgException("删除的用户名不能为空");
        }

        User deletedUser = getByName(name);
        if (deletedUser == null) {
            throw new MsgException("删除的用户名不存在");
        }

        userMapper.deleteByMap(Map.of("name", name));
        return deletedUser;
    }

    @Override
    @Cacheable(key = "'list-' + #containsPassword")
    public String getAllUsersAsString(boolean containsPassword) {
        List<User> users = userMapper.selectList(null);

        var sb = new StringBuilder("所有用户信息如下：\n\n");
        String blank = "\t\t\t";
        String endLine = "\n\n";

        sb.append("id").append(blank)
                .append("姓名").append(blank)
                .append("年龄").append(!containsPassword ? "" : blank + "密码")
                .append(endLine);

        for (var u : users) {
            sb.append(u.getId()).append(blank)
                    .append(u.getName()).append(blank)
                    .append(u.getAge()).append(!containsPassword ? "" : blank + u.getPassword())
                    .append(endLine);
        }
        return sb.toString();
    }
}
