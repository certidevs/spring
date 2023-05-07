package com.example;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        var repo = context.getBean(EmployeeRepository.class);
        List<Employee> employees = List.of(
                new Employee(null, "emp1", 20, LocalDate.of(1999, 1, 1)),
                new Employee(null, "emp2", 30, LocalDate.of(1980, 1, 1)),
                new Employee(null, "emp3", 40, LocalDate.of(1973, 1, 1)),
                new Employee(null, "emp4", 30, LocalDate.of(1960, 1, 1)),
                new Employee(null, "emp5", 50, LocalDate.of(1960, 1, 1)),
                new Employee(null, "emp6", 30, LocalDate.of(1960, 1, 1)),
                new Employee(null, "emp7", 30, LocalDate.of(1960, 1, 1))
        );
        repo.saveAll(employees);

//        repo.findAll().forEach(System.out::println);

//        repo.findAll().forEach(employee -> {
//           repo.findById(employee.getId());
//        });


//        repo.findById("8e3b627d-0474-4c22-93a3-c62d9b976bf3").ifPresent(System.out::println);
        repo.findTop3ByAge(30).forEach(System.out::println);

        var redis = context.getBean(RedisTemplate.class);

    }

}
