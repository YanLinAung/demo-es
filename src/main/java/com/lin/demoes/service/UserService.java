package com.lin.demoes.service;

import com.lin.demoes.mapper.UserMapper;
import com.lin.demoes.model.EsUser;
import com.lin.demoes.model.User;
import com.lin.demoes.repository.EsUserRepository;
import com.lin.demoes.repository.UserRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<EsUser> query(String query){
        return elasticsearchRestTemplate.queryForList(new StringQuery(query), EsUser.class);
    }
}
