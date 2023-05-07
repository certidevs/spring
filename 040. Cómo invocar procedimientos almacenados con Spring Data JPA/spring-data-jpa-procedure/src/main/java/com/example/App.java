package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        var repo = ctx.getBean(EmployeeRepository.class);
        var service = ctx.getBean(EmployeeService.class);

        repo.saveAll(List.of(
                new Employee(null, "emp1@company.com", 2000.0),
                new Employee(null, "emp2@company.com", 3000.0),
                new Employee(null, "emp3@company.com", 4000.0),
                new Employee(null, "emp4@company.com", 5000.0)
        ));

        service.moveToHistory(1L);
        service.findAllBySalaryAfterProcedure(3000.0).forEach(System.out::println);
    }

}
