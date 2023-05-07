package com.example;

import com.example.model.UserEntity;
import com.example.repository.UserEntityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        var repo = ctx.getBean(UserEntityRepository.class);
        var encoder = ctx.getBean(PasswordEncoder.class);

        var user1 = new UserEntity("user1", encoder.encode("user1"), "user1@email");
        var user2 = new UserEntity("user2", encoder.encode("user2"), "user2@email");

        repo.saveAll(List.of(user1, user2));

    }

}
