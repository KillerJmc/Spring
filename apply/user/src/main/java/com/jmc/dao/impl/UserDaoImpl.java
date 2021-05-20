package com.jmc.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmc.common.ConstName;
import com.jmc.dao.UserDao;
import com.jmc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jmc
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private JedisPool jedisPool;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public List<User> getCacheUserList() {
        try (Jedis jedis = jedisPool.getResource()) {
            String userListJson = jedis.get(ConstName.USER_LIST_JASON_NAME);

            if (userListJson == null) {
                return new ArrayList<>();
            }

            var mapper = new ObjectMapper();
            var userListType = mapper.getTypeFactory().constructCollectionType(List.class, User.class);

            return mapper.readValue(userListJson, userListType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void setCacheUserList(List<User> userList) {
        try (Jedis jedis = jedisPool.getResource()) {
            var mapper = new ObjectMapper();
            String userListJson = mapper.writeValueAsString(userList);

            jedis.set(ConstName.USER_LIST_JASON_NAME, userListJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUserList() {
        try {
            return jdbcTemplate.query(
                    "select * from user",
                    new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            List<User> userList = getCacheUserList();

            // 优先从redis中取
            for (var u : userList) {
                if (u.getId() == id) {
                    return u;
                }
            }

            User u = jdbcTemplate.queryForObject(
                    "select * from user where id=?",
                    new BeanPropertyRowMapper<>(User.class),
                    id);

            // 存到redis减小mysql压力
            userList.add(u);
            setCacheUserList(userList);

            return u;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByName(String name) {
        List<User> userList = getCacheUserList();

        // 优先从redis中取
        for (var u : userList) {
            if (u.getName().equals(name)) {
                return u;
            }
        }

        try {
            User u = jdbcTemplate.queryForObject(
                    "select * from user where name=?",
                    new BeanPropertyRowMapper<>(User.class),
                    name);

            // 存到redis减小mysql压力
            userList.add(u);
            setCacheUserList(userList);

            return u;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized void addUser(User u) {
        jdbcTemplate.update(
                "insert into user(name, age, password) values(?, ?, ?)",
                u.getName(), u.getAge(), u.getPassword());
        Integer latestId = jdbcTemplate.queryForObject("select max(id) from user", Integer.class);

        if (latestId != null) {
            // 同步存到redis中
            List<User> userList = getCacheUserList();
            u.setId(latestId);
            userList.add(u);
            setCacheUserList(userList);
        }
    }

    @Override
    public synchronized void deleteUserById(int id) {
        jdbcTemplate.update("delete from user where id=?", id);
        // 删除redis中的该对象
        List<User> userList = getCacheUserList();
        userList.removeIf(u -> u.getId() == id);
        setCacheUserList(userList);
    }

    @Override
    public synchronized void deleteUserByName(String name) {
        jdbcTemplate.update("delete from user where name=?", name);
        // 删除redis中的该对象
        List<User> userList = getCacheUserList();
        userList.removeIf(u -> u.getName().equals(name));
        setCacheUserList(userList);
    }

    @Override
    public synchronized void updateUserById(User u) {
        jdbcTemplate.update(
                "update user set name=?, password=?, age=? where id=?",
                u.getName(), u.getPassword(), u.getAge(), u.getId());

        // 更新redis中的该对象
        List<User> userList = getCacheUserList();
        for (var user : userList) {
            if (user.getId() == u.getId()) {
                user.setName(u.getName());
                user.setAge(u.getAge());
                user.setPassword(u.getPassword());
                break;
            }
        }
        setCacheUserList(userList);
    }
}
