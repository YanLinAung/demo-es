package com.lin.demoes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;


@RestController("user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public User find(@PathParam("id") String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }
}
