package com.lin.demoes.service;

import com.lin.demoes.model.User;
import com.lin.demoes.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(email + " not exist"));
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void removeAll(){
        userRepository.deleteAll();
    }
}
