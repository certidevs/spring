package com.example;

import com.example.model.Product;
import com.example.model.UserAuthority;
import com.example.model.UserEntity;
import com.example.repository.ProductRepository;
import com.example.repository.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            UserEntityRepository userRepo,
            ProductRepository productRepo,
            PasswordEncoder encoder
    ){
        return args -> {
            UserEntity user1 = new UserEntity(
                    "user1", encoder.encode("1234"), "user1@localhost", new ArrayList<>(List.of(UserAuthority.ADMIN, UserAuthority.USER))
            );
            UserEntity user2 = new UserEntity(
                    "user2", encoder.encode("1234"), "user2@localhost", new ArrayList<>(List.of(UserAuthority.USER))
            );
            userRepo.save(user1);
            userRepo.save(user2);

            List<Product> products = List.of(
                    new Product("Prod1", 3.99, "https://dummyimage.com/200x200/fff/aaa"),
                    new Product("Prod2", 4.99, "https://dummyimage.com/200x200/fff/aaa"),
                    new Product("Prod3", 7.99, "https://dummyimage.com/200x200/fff/aaa"),
                    new Product("Prod4", 8.99, "https://dummyimage.com/200x200/fff/aaa"),
                    new Product("Prod5", 9.99, "https://dummyimage.com/200x200/fff/aaa"),
                    new Product("Prod6", 1.99, "https://dummyimage.com/200x200/fff/aaa")
            );

            productRepo.saveAll(products);

        };


    }

}
