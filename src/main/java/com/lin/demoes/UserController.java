package com.lin.demoes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/user")
public class UserController {

    @GetMapping
    public String get(){
        return "user get";
    }

    @PostMapping
    public String post(){
        return "another";
    }
}
