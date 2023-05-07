package com.example;

import com.example.propagation.model.Employee;
import com.example.propagation.model.EmployeeAddress;
import com.example.propagation.repository.EmployeeRepository;
import com.example.propagation.service.CompanyService;
import com.example.propagation.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PropagationTest {

    @Autowired
    CompanyService companyService;

    @Autowired
    EmployeeRepository employeeRepository;


    @Test
    void propagation() {
        long employees = employeeRepository.count();

        Employee emp = new Employee(null, "emp1", "cat1");
        EmployeeAddress address = new EmployeeAddress(null, "address1", "city1");

        companyService.joinCompany(emp, address); // Buscar CompanyService.joinCompany

        assertEquals(employees + 1, employeeRepository.count());

    }


}
