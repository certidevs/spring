package com.example.service;

import com.example.model.Vehicle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleService {

    Flux<Vehicle> findAll();

    Flux<Vehicle> findAllByModel(String model);

    Mono<Vehicle> create(Vehicle vehicle);

    Mono<Vehicle> update(Long id, Mono<Vehicle> vehicle);

    Mono<Void> deleteById(Long id);
}
