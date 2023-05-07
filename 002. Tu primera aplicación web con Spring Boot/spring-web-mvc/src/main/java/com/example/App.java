package com.example;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        var repository = context.getBean(ProductRepository.class);

        List<Product> products = List.of(
                new Product(null, "product1", 5.99, 1),
                new Product(null, "product2", 6.99, 2),
                new Product(null, "product3", 7.99, 4),
                new Product(null, "product4", 8.99, 2),
                new Product(null, "product5", 8.99, 2),
                new Product(null, "product6", 8.99, 2)
        );
        repository.saveAll(products);
    }

}
