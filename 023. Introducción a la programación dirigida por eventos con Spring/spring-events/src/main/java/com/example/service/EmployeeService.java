package com.example.service;


import com.example.model.Employee;

public interface EmployeeService {
    Employee create(Employee employee);
    void deleteById(Long id);
}
