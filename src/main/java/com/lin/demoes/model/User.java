package com.lin.demoes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class User {

    @Id
    private String id;

    @NonNull
    @Indexed(unique = true)
    private String email;

    private Set<String> friends = new HashSet<>();

    private Set<String> subscribes = new HashSet<>();

    private Set<String> blocks = new HashSet<>();
}
