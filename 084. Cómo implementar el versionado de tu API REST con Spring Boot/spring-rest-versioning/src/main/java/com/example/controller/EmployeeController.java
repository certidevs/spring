package com.example.controller;

import com.example.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Enfoque basado en query params en la url
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @GetMapping(value = "/employees/{id}", params = "version=1")
    public Employee findByIdV1(@PathVariable Long id){
        return new Employee(1L, "emp v1");
    }

    @GetMapping(value = "/employees/{id}", params = "version=2")
    public Employee findByIdV2(@PathVariable Long id){
        return new Employee(1L, "emp v2");
    }
}
