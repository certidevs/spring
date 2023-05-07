package com.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Override
    public List<Employee> findAll() {
        return List.of(
                new Employee(1L, "emp1")
        );
    }
}
