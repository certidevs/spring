package com.example.lock.pessimistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class VehicleClient {

    @Autowired
    private VehicleRepository repo;

    @Autowired
    private VehicleService service;

    public ExecutorService run() {
        Vehicle vehicle1 = new Vehicle(null, "Ford mondeo");
        repo.save(vehicle1);

        ExecutorService es = Executors.newFixedThreadPool(2);

        //user 1, reader
        es.execute(service::find1);

        //user 2, writer
        es.execute(service::find2);

        return es;
    }
}
