package com.lin.demoes;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class User {

    @Id
    private String id;

    @NonNull
    private String email;

    private List<String> friends = new ArrayList<>();
}
