package com.example.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> findAll(){
        List<Customer> customers = this.repository.findAll();
        if (customers.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id){
        return this.repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        if(customer.getId() != null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(this.repository.save(customer));
    }

    @PutMapping("/customers")
    public ResponseEntity<Customer> update(@RequestBody Customer customer){
        if (customer == null || customer.getId() == null)
            throw new BadRequestException("id customer can not be null");

        Optional<Customer> customerOpt = repository.findById(customer.getId());
        if (customerOpt.isEmpty())
            return ResponseEntity.notFound().build();

        Customer customerDB = customerOpt.get();

        customerDB.setName(customer.getName());
        customerDB.setAge(customer.getAge());

        return ResponseEntity.ok(repository.save(customerDB));
    }

    @DeleteMapping("/customers/{identifier}")
    public ResponseEntity<Void> deleteById(@PathVariable("identifier") Long id){
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();

        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
