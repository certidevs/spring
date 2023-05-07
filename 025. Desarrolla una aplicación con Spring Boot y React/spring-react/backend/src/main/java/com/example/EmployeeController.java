package com.example;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmployeeController {

    @GetMapping
    public List<Employee> findAll(){
        return List.of(
                new Employee(1L, "emp1", "rol1"),
                new Employee(2L, "emp2", "rol2"),
                new Employee(3L, "emp3", "rol3")
        );
    }
}
