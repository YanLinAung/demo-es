package com.lin.demoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
}
