package com.example.repository;

import com.example.model.Vehicle;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VehicleRepository extends ReactiveMongoRepository<Vehicle, String> {

    @Query("{ 'released': ?0 }")
    Flux<Vehicle> findAllByReleased(Integer released);
}

