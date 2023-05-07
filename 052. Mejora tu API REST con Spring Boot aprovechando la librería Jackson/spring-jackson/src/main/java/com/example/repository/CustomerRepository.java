package com.example.repository;

import com.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // join fetch vehicles
    @Query("select distinct c from Customer c join fetch c.vehicles")
    List<Customer> findWithVehicles();
}