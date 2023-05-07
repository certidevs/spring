package com.example.lock.optimistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public void find1() {
        System.out.println(" -- user1 updating name --");
        Customer customer = repo.findById(1L).get();
        System.out.println("user1 loaded entity: " + customer);
        customer.setName("customeredited1");
        this.sleep(1000);
        try {
            repo.save(customer);
        } catch (Exception e) {
            System.err.println("user1 " + e);
            System.out.println("user1 after error: " + repo.findById(1L).get());
            return;
        }
        System.out.println("user1 finished: " + repo.findById(1L).get());
    }


    public void find2() {
        System.out.println(" -- user2 updating name --");
        Customer customer = repo.findById(1L).get();
        System.out.println("user2 loaded entity: " + customer);
        customer.setName("customeredited2");
        try {
            repo.save(customer);
        } catch (Exception e) {
            System.err.println("user2: " + e);
            System.out.println("user2 after error: " + repo.findById(1L).get());
            return;
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
