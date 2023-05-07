package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.ENDING;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.STARTING;
import static org.springframework.data.domain.ExampleMatcher.matching;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        var repo = context.getBean(ProductRepository.class);
        var dao = context.getBean(ProductDAO.class);

        repo.saveAll(List.of(
                new Product(null, "silla eléctrica gaming", 33.33, LocalDate.of(2000, 1, 1), true, "Ford", "red"),
                new Product(null, "silla colgante gaming", 43.33, LocalDate.of(2001, 1, 1), true, "Ford", "green"),
                new Product(null, "silla oficina ", 56.33, LocalDate.of(2002, 1, 1), true, "Ford", "blue"),
                new Product(null, "silla despacho", 56.33, LocalDate.of(2002, 1, 1), true, "Ford", "blue"),
                new Product(null, "silla sofá", 63.33, LocalDate.of(2003, 1, 1), true, "Honda", "yellow"),
                new Product(null, "laptop gaming", 73.33, LocalDate.of(2004, 1, 1), true, "Tesla USA", "red"),
                new Product(null, "mesa plegable PREMIUM", 1200.0, LocalDate.of(2005, 1, 1), true, "Tesla Europe", "red")
        ));

        // SPECIFICATION API
        System.out.println("\n findAllByManufacturerLike");
        dao.findAllByManufacturerLike("Tesla").forEach(System.out::println);

        System.out.println("\n findAllByIdIn");
        dao.findAllByIdIn(List.of(1L, 3L, 6L)).forEach(System.out::println);

        System.out.println("\n findAllByPriceBetween");
        dao.findAllByPriceBetween(50.0, 75.0).forEach(System.out::println);


        System.out.println("\n findAllPremium");
        dao.findAllPremium().forEach(System.out::println);

        // QUERY BY EXAMPLE API

        System.out.println("\n findAll example Ford");
        Product product = new Product(null, null, null, null, null, "Ford", null);
        Example<Product> example = Example.of(product);
        repo.findAll(example).forEach(System.out::println);

        System.out.println("\n findAll example Ford blue");
        product = new Product(null, null, null, null, null, "Ford", "blue");
        repo.findAll(Example.of(product)).forEach(System.out::println);

        System.out.println("\n findAll withIgnorePaths name");
        product = new Product(null, "silla despacho", null, null, null, "Ford", "blue");
        repo.findAll(Example.of(product, matching().withIgnorePaths("name"))).forEach(System.out::println);

        System.out.println("\n findAll withStringMatcher ENDING");
        product = new Product(null, "gaming", null, null, null, null, null);
        example = Example.of(product, matching().withStringMatcher(ENDING));
        repo.findAll(example).forEach(System.out::println);
    }

}
