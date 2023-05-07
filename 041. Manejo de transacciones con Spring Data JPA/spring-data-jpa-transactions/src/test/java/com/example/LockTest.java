package com.example;

import com.example.lock.optimistic.CustomerClient;
import com.example.lock.pessimistic.VehicleClient;
import com.example.propagation.model.Employee;
import com.example.propagation.model.EmployeeAddress;
import com.example.propagation.repository.EmployeeRepository;
import com.example.propagation.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LockTest {

    @Autowired
    VehicleClient vehicleClient;

    @Autowired
    CustomerClient customerClient;


    @Test
    void pessimisticLock() throws InterruptedException {
        ExecutorService es = vehicleClient.run();
        es.shutdown();
        es.awaitTermination(5, TimeUnit.MINUTES);
    }

    @Test
    void optimisticLock() throws InterruptedException {
        ExecutorService es = customerClient.run();
        es.shutdown();
        es.awaitTermination(5, TimeUnit.MINUTES);
    }
}
