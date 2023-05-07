package com.example;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        var repo = ctx.getBean(EmployeeRepository.class);

        repo.saveAll(List.of(
                new Employee(null, "emp1@company.com"),
                new Employee(null, "emp2@company.com"),
                new Employee(null, "emp3@company.com")
        ));

        repo.findAll().forEach(System.out::println);
    }

}
