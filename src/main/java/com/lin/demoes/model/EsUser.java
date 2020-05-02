package com.lin.demoes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.HashSet;
import java.util.Set;

@Document(indexName = "users", type = "article")
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class EsUser {
    @Id
    private String id;

    @NonNull
    private String email;

    private Set<String> friends = new HashSet<>();

    private Set<String> subscribes = new HashSet<>();

    private Set<String> blocks = new HashSet<>();
}
