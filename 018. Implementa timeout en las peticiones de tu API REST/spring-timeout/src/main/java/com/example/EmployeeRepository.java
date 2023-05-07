package com.example;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    List<Employee> findAll(){

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return List.of(
                new Employee(null, "emp1", 40000.0),
                new Employee(null, "emp2", 30000.0),
                new Employee(null, "emp3", 50000.0),
                new Employee(null, "emp4", 60000.0)
        );
    }
}
