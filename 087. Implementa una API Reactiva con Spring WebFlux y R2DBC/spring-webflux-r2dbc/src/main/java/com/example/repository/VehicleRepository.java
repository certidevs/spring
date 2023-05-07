package com.example.repository;

import com.example.model.Vehicle;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VehicleRepository extends R2dbcRepository<Vehicle, Long> {

    @Query("SELECT * FROM vehicles WHERE released = :released")
    Flux<Vehicle> findAllByReleased(Integer released);
}

