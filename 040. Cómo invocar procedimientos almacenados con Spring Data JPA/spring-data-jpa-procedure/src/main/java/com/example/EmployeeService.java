package com.example;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void moveToHistory(Long id) {
        employeeRepository.moveToHistory(id);
    }

    public List<Employee> findAllBySalaryAfterProcedure(Double salary) {
        return employeeRepository.findAllBySalaryAfterProcedure(salary);
    }
}
