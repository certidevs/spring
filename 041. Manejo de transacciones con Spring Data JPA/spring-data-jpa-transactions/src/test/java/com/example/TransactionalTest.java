package com.example;

import com.example.propagation.model.Employee;
import com.example.propagation.repository.EmployeeRepository;
import com.example.propagation.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransactionalTest {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;


    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        employeeRepository.saveAll(List.of(
                new Employee(null, "emp1@company.com", "cat1"),
                new Employee(null, "emp2@company.com", "cat1"),
                new Employee(null, "emp3@company.com", "cat2"),
                new Employee(null, "emp4@company.com", "cat2"),
                new Employee(null, "emp5@company.com", "cat3"),
                new Employee(null, "emp6@company.com", "cat4")));
    }

    @Test
    void readOnlyTransaction() {
        var employees = employeeService.findAllByCategories(List.of("cat1", "cat2"));
        int size = employees.values().stream().flatMap(List::stream).toList().size();
        System.out.println(size);

    }

    @Test
    void modifyTransaction() {
        assertEquals(6, employeeRepository.count());

        employeeService.deleteByCategory("cat2");

        assertEquals(4, employeeRepository.count());

    }

    @Test
    void rollbackTransaction() {
        long count = employeeRepository.count();
        try {
//            employeeService.saveWithoutRollback();
//            assertEquals(count + 3, employeeRepository.count());

            employeeService.saveWithRollback();
            assertEquals(count, employeeRepository.count());

        } catch (Exception e) {
        }
    }
}
