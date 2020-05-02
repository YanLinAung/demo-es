package com.lin.demoes.repository;

import com.lin.demoes.model.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsUserRepository extends ElasticsearchRepository<EsUser, String> {
}
