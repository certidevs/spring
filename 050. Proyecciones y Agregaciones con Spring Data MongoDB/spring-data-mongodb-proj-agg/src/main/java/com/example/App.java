package com.example;

import com.example.dao.ProductDAO;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        var repo = ctx.getBean(ProductRepository.class);
        var dao = ctx.getBean(ProductDAO.class);
        repo.deleteAll();

        var prod1 = new Product(null, "prod1", 10.0, 10, "cat1", true, "man1");
        var prod2 = new Product(null, "prod2", 10.0, 10, "cat2", true, "man1");
        var prod3 = new Product(null, "prod3", 100.0, 10, "cat1", true, "man2");
        var prod4 = new Product(null, "prod4", 100.0, 10, "cat2", true, "man2");
        repo.saveAll(List.of(prod1, prod2, prod3, prod4));

        System.out.println("\nfindByCategoryAndPriceGreaterThanEqual");
        repo.findByCategoryAndPriceGreaterThanEqual("cat1", 50.0).forEach(System.out::println);

        System.out.println("\ngroupPriceByCategory");
        repo.groupPriceByCategory().forEach(System.out::println);

        System.out.println("\nsumQuantities");
        System.out.println(repo.sumQuantities());

        System.out.println("\nincrementPriceByManufacturer");
        repo.incrementPriceByManufacturer("man1", 10.0);
        repo.findAll().forEach(System.out::println);

        System.out.println("\nfindAllManufacturers");
        repo.findAllManufacturers().forEach(System.out::println);

        System.out.println("\navgPrice");
        System.out.println(repo.avgPrice());

        System.out.println("\ntotalPrice");
        System.out.println(repo.totalPrice());

        System.out.println("\ngroupPrice");
        dao.groupPrice("cat1").forEach(System.out::println);;
    }

}
