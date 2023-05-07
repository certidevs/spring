package com.example.service;

import com.example.model.Vehicle;
import com.example.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository repo;

    @Override
    public Flux<Vehicle> findAll() {
        return this.repo.findAll().map(vehicle -> {
            vehicle.setCubicCentimeters(vehicle.getCubicCentimeters() / 1000);
            return vehicle;
        });
    }

    @Override
    public Mono<Vehicle> findById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Flux<Vehicle> findAllByReleased(Integer released) {
        return this.repo.findAllByReleased(released);
    }

    @Override
    public Mono<Vehicle> save(Vehicle vehicle) {
        return this.repo.save(vehicle);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return this.repo.deleteById(id);
    }

    @Override
    public Mono<Void> delete(Vehicle vehicle) {
        return this.repo.delete(vehicle);
    }
}
