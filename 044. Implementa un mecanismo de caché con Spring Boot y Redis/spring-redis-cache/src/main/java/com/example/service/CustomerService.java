package com.example.service;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerRepository repo;

    public List<Customer> findAll(){
        return repo.findAll();
    }

    @Cacheable(value = "customers", key = "#id")
    public Customer findById(Long id){
        var customer = repo.findById(id).orElseThrow();
        logger.info("Retrieved customer from DB: {}", customer);
        return customer;
    }

    public Customer create(Customer customer){
        return repo.save(customer);
    }

    @CacheEvict(value = "customers" , key = "#id")
    public Customer update(Long id, Customer customer){
        var entity = this.findById(id);
        entity.setName(customer.getName());
        return repo.save(entity);
    }


}
