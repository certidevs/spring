package com.example;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx =SpringApplication.run(App.class, args);
        var repo = ctx.getBean(CustomerRepository.class);

        repo.saveAll(List.of(
            new Customer("cust1", "cust1@email", 4000.0, 25, false, "admin", "1234"),
            new Customer("cust2", "cust2@email", 4000.0, 25, false, "admin", "1234"),
            new Customer("cust3", "cust3@email", 4000.0, 25, false, "admin", "1234"),
            new Customer("cust4", "cust1ç4@email", 4000.0, 25, false, "admin", "1234")
        ));

    }

}
