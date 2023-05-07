package com.example;

import com.example.model.Subscription;
import com.example.model.UserAuthority;
import com.example.model.UserEntity;
import com.example.repository.SubscriptionRepository;
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

        var userRepo = ctx.getBean(UserEntityRepository.class);
        var subRepo = ctx.getBean(SubscriptionRepository.class);
        var encoder = ctx.getBean(PasswordEncoder.class);

        var sub1 = new Subscription(null, "sub1", 19.99, 5L, 1L);
        var sub2 = new Subscription(null, "sub2", 29.99, 10L, 1L);
        subRepo.saveAll(List.of(sub1, sub2));

        var user1 = new UserEntity(null, "user1", encoder.encode("admin"), "user1@email",
                List.of(UserAuthority.READ), sub1);
        var user2 = new UserEntity(null, "user2", encoder.encode("admin"), "user2@email",
                List.of(UserAuthority.WRITE), sub2);
        userRepo.saveAll(List.of(user1, user2));

    }

}
