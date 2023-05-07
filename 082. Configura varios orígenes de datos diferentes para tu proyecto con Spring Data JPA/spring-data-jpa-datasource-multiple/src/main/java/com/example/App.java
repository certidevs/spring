package com.example;

import com.example.datasource1.model.Employee;
import com.example.datasource1.repository.EmployeeRepository;
import com.example.datasource2.model.Customer;
import com.example.datasource2.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        var employeeRepo = ctx.getBean(EmployeeRepository.class);
        var customerRepo = ctx.getBean(CustomerRepository.class);

        employeeRepo.saveAll(List.of(
                new Employee(null, "John", "john@company.com"),
                new Employee(null, "mike", "mike@company.com"),
                new Employee(null, "Bob", "bob@company.com")
        ));

        employeeRepo.findAll().forEach(System.out::println);

        customerRepo.saveAll(List.of(
                new Customer(null, "John", "madrid"),
                new Customer(null, "mike", "city1"),
                new Customer(null, "Bob", "city2")
        ));

        customerRepo.findAll().forEach(System.out::println);


    }

}
