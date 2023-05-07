package com.example.controller;

import com.example.primary.CustomerService;
import com.example.service.EmployeeService;
import com.example.service.EmployeeServiceImpl;
import com.example2.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class EmployeeController {

//    @Autowired
    private EmployeeService employeeService;

//    @Autowired
    private CustomerService customerService;

    /*
otra opción es utilizar @Qualifier("customerBServiceImpl")
 */
//    @Autowired
    private HelloService helloService;

    public EmployeeController(EmployeeService employeeService, CustomerService customerService, HelloService helloService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.helloService = helloService;
    }

    public String helloFromEmployeeService(){
        return this.employeeService.hello();
    }

    public String helloFromCustomerService(){
        return this.customerService.hello();
    }
}
