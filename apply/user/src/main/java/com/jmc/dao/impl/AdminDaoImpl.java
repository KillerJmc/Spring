package com.jmc.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.jmc.dao.AdminDao;
import com.jmc.domain.Admin;
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
@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate jdbcTemplate;
    private JedisPool jedisPool;
    private final String ADMIN_LIST_JASON_NAME = "adminList";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public List<Admin> getCacheAdminList() {
        try (Jedis jedis = jedisPool.getResource()) {
            String adminListJson = jedis.get(ADMIN_LIST_JASON_NAME);

            if (adminListJson == null) {
                return new ArrayList<>();
            }

            var mapper = new ObjectMapper();
            CollectionType adminListType = mapper.getTypeFactory().constructCollectionType(List.class, Admin.class);

            return mapper.readValue(adminListJson, adminListType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void setCacheAdminList(List<Admin> adminList) {
        try (Jedis jedis = jedisPool.getResource()) {
            var mapper = new ObjectMapper();
            String adminListJson = mapper.writeValueAsString(adminList);

            jedis.set(ADMIN_LIST_JASON_NAME, adminListJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdminByName(String name) {
        List<Admin> adminList = getCacheAdminList();

        // 优先从redis中取
        for (var a : adminList) {
            if (a.getName().equals(name)) {
                return a;
            }
        }

        try {
            Admin a = jdbcTemplate.queryForObject(
                    "select * from admin where name=?",
                    new BeanPropertyRowMapper<>(Admin.class),
                    name);

            // 存到redis减小mysql压力
            adminList.add(a);
            setCacheAdminList(adminList);

            return a;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
