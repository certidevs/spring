package com.example.service;


import com.example.model.Vehicle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleService {

    Flux<Vehicle> findAll();

    Mono<Vehicle> findById(String id);

    Mono<Vehicle> create(Vehicle vehicle);

    Mono<Vehicle> update(String id, Mono<Vehicle> vehicle);

    Mono<Void> deleteById(String id);

}
