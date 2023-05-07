package com.example.propagation.repository;

import com.example.propagation.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByCategoryIn(List<String> categories);

    void deleteByCategory(String category);
}