package com.example.controller;

import com.example.model.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> findAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id){
        return service.findById(id);
    }
    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return service.create(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id,
                           @RequestBody Customer customer){
        return service.update(id, customer);
    }

}
