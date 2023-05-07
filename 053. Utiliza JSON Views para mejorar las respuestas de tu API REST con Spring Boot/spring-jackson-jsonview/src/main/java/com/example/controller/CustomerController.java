package com.example.controller;

import com.example.model.Customer;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import static com.example.model.CustomerView.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private List<Customer> customers;

    public CustomerController() {
        this.customers = List.of(
                new Customer(1L, "cust1", "cust1@gmail.com", 4000.0, "admin", "1234", 2021, true),
                new Customer(2L, "cust2", "cust2@gmail.com", 4000.0, "admin", "1234", 2021, true),
                new Customer(3L, "cust3", "cust3@gmail.com", 4000.0, "admin", "1234", 2021, true)
        );
    }

    @GetMapping
    @JsonView(CustomerList.class)
//    @JsonView(CustomerFull.class)
    public List<Customer> findAll(){
        return customers;
    }

    @GetMapping("/{id}")
    @JsonView(CustomerDetail.class)
    public Customer findById(@PathVariable Long id){
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    @GetMapping("/edit/{id}")
    @JsonView(CustomerEdit.class)
    public Customer findByIdForEdit(@PathVariable Long id){
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
