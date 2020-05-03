package com.lin.demoes.service;

import com.lin.demoes.mapper.UserMapper;
import com.lin.demoes.model.EsUser;
import com.lin.demoes.model.User;
import com.lin.demoes.repository.EsUserRepository;
import com.lin.demoes.repository.UserRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;
    private final EsUserRepository esUserRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public UserService(UserMapper userMapper,
                       UserRepository userRepository,
                       EsUserRepository esUserRepository,
                       ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.esUserRepository = esUserRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(email + " not exist"));
    }

    public void save(User user) {
        userRepository.save(user);
        esUserRepository.save(userMapper.convertToIndex(user));
    }

    public void removeAll() {
        userRepository.deleteAll();
        esUserRepository.deleteAll();
    }

    public List<EsUser> query(String query) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .should(termQuery("email", "john"))
                .should(termQuery("email", "lisa"));

        FieldSortBuilder sort = new FieldSortBuilder("email").order(SortOrder.DESC);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withSort(sort)
                .build();

        return elasticsearchRestTemplate.queryForList(searchQuery, EsUser.class);
    }

}
