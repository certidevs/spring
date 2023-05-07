package com.example.service;

import com.example.model.Vehicle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleService {

    Flux<Vehicle> findAll();

    Mono<Vehicle> findById(Long id);

    Flux<Vehicle> findAllByReleased(Integer released);

    Mono<Vehicle> save(Vehicle vehicle);

    Mono<Void> deleteById(Long id);

    Mono<Void> delete(Vehicle vehicle);
}
