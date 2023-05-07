package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        var repo = context.getBean(EmployeeRepository.class);

        List<Employee> employees = List.of(
                new Employee(null, "Mike", LocalDate.of(1990, 1, 1), 40000.0, true),
                new Employee(null, "Mike", LocalDate.of(1995, 1, 1), 50000.0, false),
                new Employee(null, "Mike", LocalDate.of(1975, 1, 1), 60000.0, true),
                new Employee(null, "An Rigoti", LocalDate.of(2001, 1, 1), 70000.0, false),
                new Employee(null, "John Doe", LocalDate.of(1999, 1, 1), 30000.0, true),
                new Employee(null, "Bob Garcia", LocalDate.of(1980, 1, 1), 40000.0, true)
        );
        repo.saveAll(employees);
    }

}
