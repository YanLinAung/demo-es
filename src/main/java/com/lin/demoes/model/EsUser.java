package com.lin.demoes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashSet;
import java.util.Set;

@Document(indexName = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class EsUser {
    @Id
    private String id;

    @NonNull
    @Field(type = FieldType.Text, fielddata = true)
    private String email;

    private Set<String> friends = new HashSet<>();

    private Set<String> subscribes = new HashSet<>();

    private Set<String> blocks = new HashSet<>();
}
