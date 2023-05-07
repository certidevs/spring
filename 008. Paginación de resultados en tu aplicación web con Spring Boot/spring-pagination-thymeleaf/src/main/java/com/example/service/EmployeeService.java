package com.example.service;

import com.example.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Page<Employee> findAllPaginated(int pageNum, int pageSize, String sortField, String sortDirection);
    Employee findById(Long id);

    Employee save(Employee employee);
    void deleteById();



}
