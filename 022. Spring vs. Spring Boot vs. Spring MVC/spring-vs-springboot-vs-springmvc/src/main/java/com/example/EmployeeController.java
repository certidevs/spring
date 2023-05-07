package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
//@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public List<Employee> findAll(){
        return employeeService.findAll();
    }
}
