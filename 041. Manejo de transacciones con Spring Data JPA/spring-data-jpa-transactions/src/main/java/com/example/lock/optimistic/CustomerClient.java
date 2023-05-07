package com.example.lock.optimistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CustomerClient {

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private CustomerService service;

    public ExecutorService run() {
        Customer customer = new Customer(null, "customer1");
        repo.save(customer);

        ExecutorService es = Executors.newFixedThreadPool(2);

        //user 1, reader
        es.execute(service::find1);

        //user 2, writer
        es.execute(service::find2);

        return es;
    }

}
