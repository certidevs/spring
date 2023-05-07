package com.example.propagation.service;

import com.example.propagation.model.Employee;
import com.example.propagation.model.EmployeeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeAddressService employeeAddressService;

    @Transactional
    public void joinCompany(Employee employee, EmployeeAddress employeeAddress) {
        employeeService.insert(employee);
        employeeAddressService.insert(employeeAddress);
    }

}
