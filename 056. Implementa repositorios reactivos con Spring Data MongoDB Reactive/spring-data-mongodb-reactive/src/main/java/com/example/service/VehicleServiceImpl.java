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
    private VehicleRepository repository;

    @Override
    public Flux<Vehicle> findAll() {
        return this.repository.findAll().map(vehicle -> {
            vehicle.setReleased(vehicle.getReleased() + 1);
            vehicle.setModel(vehicle.getModel().toUpperCase());
            return vehicle;
        });
    }

    @Override
    public Mono<Vehicle> findById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Mono<Vehicle> create(Vehicle vehicle) {
        return this.repository.save(vehicle);
    }

    @Override
    public Mono<Vehicle> update(String id, Mono<Vehicle> vehicle) {
        return this.repository.findById(id).
                flatMap(v -> vehicle.map(v2 -> {
                    v.setModel(v2.getModel());
                    v.setCubicCentimeters(v2.getCubicCentimeters());
                    v.setReleased(v2.getReleased());
                    return v;
                })).flatMap(this.repository::save);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return this.repository.deleteById(id);
    }
}
