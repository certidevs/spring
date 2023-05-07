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
        ApplicationContext context = SpringApplication.run(App.class, args);
        var repo = context.getBean(EmployeeRepository.class);
        List<Employee> employees = List.of(
                new Employee(null, "employee1", "lastname1", "1@gmail.com"),
                new Employee(null, "employee2", "lastname2", "2@gmail.com"),
                new Employee(null, "employee3", "lastname3", "3@gmail.com"),
                new Employee(null, "employee4", "lastname4", "4@gmail.com"),
                new Employee(null, "employee5", "lastname5", "5@gmail.com"),
                new Employee(null, "employee6", "lastname6", "6@gmail.com"),
                new Employee(null, "employee7", "lastname7", "7@gmail.com"),
                new Employee(null, "employee8", "lastname8", "8@gmail.com"),
                new Employee(null, "employee9", "lastname9", "9@gmail.com"),
                new Employee(null, "employee10", "lastname10", "10@gmail.com")
        );
        repo.saveAll(employees);
    }

}
