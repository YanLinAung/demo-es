package com.lin.demoes;

import com.lin.demoes.model.User;
import com.lin.demoes.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Log4j2
public class DemoEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoEsApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(UserService userService) {
        return args -> {
            userService.removeAll();

            userService.save(User.of("andy@example.com"));
            userService.save(User.of("john@example.com"));
            userService.save(User.of("common@example.com"));
            userService.save(User.of("lisa@example.com"));
            userService.save(User.of("kate@example.com"));

            log.info("Initial User db setup done");
        };
    }
}
