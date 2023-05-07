package com.example.propagation.service;

import com.example.propagation.model.Employee;
import com.example.propagation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    @Transactional(readOnly = true)
    public Map<String, List<Employee>> findAllByCategories(List<String> categories){
        return repository.findAllByCategoryIn(categories).stream()
                .collect(Collectors.groupingBy(Employee::getCategory));
    }

    @Transactional(rollbackFor = SQLException.class)
    public void saveWithRollback() throws SQLException {
        this.repository.save(new Employee(null, "emp6", "cat1"));
        this.repository.save(new Employee(null, "emp7", "cat2"));
        this.saveWithException();
    }
    public void saveWithoutRollback() throws SQLException {
        this.repository.save(new Employee(null, "emp6", "cat1"));
        this.repository.save(new Employee(null, "emp7", "cat2"));
        this.saveWithException();
    }


    public void saveWithException() throws SQLException {
        this.repository.save(new Employee(null, "emp8", "cat2"));
        throw new SQLException("Throwing exception for demoing Rollback!!!");
    }

    public void insert(Employee user) {
        repository.save(user);
    }



    @Transactional
    public void deleteByCategory(String category) {
        repository.deleteByCategory(category);
    }
}
