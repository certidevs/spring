package com.example.lock.pessimistic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VehicleService {


    @Autowired
    private VehicleRepository repo;

    public void find1() {
        System.out.println(" -- user 1 reading Vehicle entity --");
        long start = System.currentTimeMillis();
        Vehicle vehicle = null;
        try {
            vehicle = repo.find1(1L);
        } catch (Exception e) {
            System.err.println("User 1 got exception while acquiring the database lock:\n " + e);
            return;
        }
        System.out.println(" -- user 1 get lock,  time was: --"  + (System.currentTimeMillis() - start));
        this.sleep(6000);
        System.out.println("User 1 read vehicle: " + vehicle);

    }


    public void find2() {
        this.sleep(500); // let user1 acquire optimistic lock first
        System.out.println(" -- user 2 writing Vehicle entity --");
        long start = System.currentTimeMillis();
        Vehicle vehicle;
        try {
            vehicle = repo.find2(1L);
        } catch (Exception e) {
            System.err.println("User 2 got exception while acquiring the database lock:\n " + e);
            return;
        }
        System.out.println(" -- user 2 get lock,  time was: --"  + (System.currentTimeMillis() - start));
        vehicle.setModel("updated model by user 2.");
        repo.save(vehicle);
        System.out.println("User 2 updated vehicle: " + vehicle);

    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
