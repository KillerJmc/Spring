package com.jmc.es.dao;

import com.jmc.es.pojo.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDao extends ElasticsearchRepository<User, Long> {
}
