package com.example.controller;

import com.example.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
Enfoque versionado a través de la URI

Opción 1: A nivel de método
Opción 2: A nivel de clase creando controladores separados

A mayores se documentaría la API con Open API / Swagger
 */
@RestController
@RequestMapping("/api")
//@RequestMapping("/api/v1/customers")
public class CustomerController {

    List<Customer> customers;

    public CustomerController() {
        customers = new ArrayList<>(List.of(
                new Customer(1L, "cust1", true, "A"),
                new Customer(2L, "cust2", true, "B"),
                new Customer(3L, "cust3", true, "C"),
                new Customer(4L, "cust4", true, "D")
        ));
    }

    @GetMapping("v1/customers")
    public List<Customer> findAll(){
        return customers;
    }

    @GetMapping("v2/customers")
    public List<Customer> findAllV2(){
        return customers.stream()
                .filter(Customer::active)
                .map(this::calcBonus)
                .toList();
    }

    private Customer calcBonus(Customer customer) {
        Double bonus = switch (customer.category()) {
            case "A" -> 100.0;
            case "B" -> 200.0;
            case "C" -> 300.0;
            case "D" -> 400.0;
            default -> 0.0;
        };
        return new Customer(customer.id(), customer.name(), customer.active(), customer.category(), bonus);
    }

}
