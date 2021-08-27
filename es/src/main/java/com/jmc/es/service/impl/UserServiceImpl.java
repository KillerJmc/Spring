package com.jmc.es.service.impl;

import com.jmc.es.dao.UserDao;
import com.jmc.es.pojo.User;
import com.jmc.es.service.UserService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final ElasticsearchRestTemplate restTemplate;

    private void init() {
        userDao.saveAll(List.of(
                User.builder().id(1).name("Merry").age(18).gender("女").build(),
                User.builder().id(2).name("Jmc").age(21).gender("男").build(),
                User.builder().id(3).name("Jenny").age(24).gender("女").build(),
                User.builder().id(4).name("Lucy").age(16).gender("女").build(),
                User.builder().id(5).name("Jack").age(28).gender("男").build(),
                User.builder().id(6).name("Jerry").age(33).gender("男").build()
        ));
    }

    @Override
    public User getById(Long id) {
        if (userDao.count() == 0) {
            init();
        }

        return userDao.findById(id).orElse(null);
    }

    @Override
    public List<User> search(String field, String value) {
        var searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery(field, value))
                .build();

        return restTemplate.search(searchQuery, User.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }

    @Override
    public List<User> complexSearch() {
        // 查询性别为女且年龄小于20岁的人，按年龄升序排列，高亮显示性别，并且分页每页一个人，显示第二页内容
        var searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                                .must(QueryBuilders.matchQuery("gender", "女"))
                                .filter(QueryBuilders.rangeQuery("age").lt(20)))
                .withSort(SortBuilders.fieldSort("age").order(SortOrder.ASC))
                .withHighlightFields(new HighlightBuilder.Field("gender"))
                .withPageable(PageRequest.of(1, 1))
                .build();


        return restTemplate.search(searchQuery, User.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
