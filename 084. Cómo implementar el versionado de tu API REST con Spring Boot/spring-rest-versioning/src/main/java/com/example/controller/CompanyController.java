package com.example.controller;

import com.example.model.Company;
import com.example.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyController {

    // HTTP Header Accept
    @GetMapping(value = "/companies/{id}", produces = "application/vnd.company.app-v1+json")
    public Company findByIdV1(@PathVariable Long id){
        return new Company(1L, "company v1");
    }

    @GetMapping(value = "/companies/{id}", produces = "application/vnd.company.app-v2+json")
    public Company findByIdV2(@PathVariable Long id){
        return new Company(1L, "company v2");
    }

}
