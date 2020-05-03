package com.lin.demoes;

import com.lin.demoes.model.User;
import com.lin.demoes.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoEsApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(UserService userService) {
        return args -> {
            userService.removeAll();
            User user = User.of("email");
            userService.save(user);

            User result = userService.findByEmail("email");
            System.out.println("done -> " + result.getId());
        };
    }
}
