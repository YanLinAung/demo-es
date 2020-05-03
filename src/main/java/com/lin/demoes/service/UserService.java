package com.lin.demoes.service;

import com.lin.demoes.mapper.UserMapper;
import com.lin.demoes.model.EsUser;
import com.lin.demoes.model.User;
import com.lin.demoes.repository.EsUserRepository;
import com.lin.demoes.repository.UserRepository;
import com.lin.demoes.request.FilterItem;
import com.lin.demoes.request.FriendQueryRequest;
import com.lin.demoes.request.SortRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

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

    public List<EsUser> query(FriendQueryRequest query) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        if(query.getFilter().getLogic().equalsIgnoreCase("OR")) {
            for (FilterItem filter : query.getFilter().getFilters()) {
                boolQueryBuilder
                        .should(termQuery(filter.getField(), filter.getValue().replaceAll("\\*", "")));
            }
        } else {
            for (FilterItem filter : query.getFilter().getFilters()) {
                boolQueryBuilder
                        .must(termQuery(filter.getField(), filter.getValue()));
            }
        }

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder);

        for (SortRequest sortRequest : query.getSort()) {
            if(sortRequest.getDir().equalsIgnoreCase("desc")) {
                builder.withSort(new FieldSortBuilder(sortRequest.getField()).order(SortOrder.DESC));
            } else {
                builder.withSort(new FieldSortBuilder(sortRequest.getField()).order(SortOrder.ASC));
            }
        }

        return elasticsearchRestTemplate.queryForList(builder.build(), EsUser.class);
    }

}
