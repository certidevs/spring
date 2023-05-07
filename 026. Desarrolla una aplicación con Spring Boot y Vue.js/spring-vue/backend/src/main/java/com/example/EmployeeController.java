package com.example;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080/"})
public class EmployeeController {


    @GetMapping("/api/employees")
    public List<Employee> findAll(){
        return List.of(
                new Employee(1L, "emp1", "role1"),
                new Employee(2L, "emp2", "role2"),
                new Employee(3L, "emp3", "role3")
        );

    }
}
