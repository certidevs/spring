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
        return repository.findAll().map(vehicle -> {
            vehicle.setModel(vehicle.getModel().toUpperCase());
            return vehicle;
        });
    }

    @Override
    public Flux<Vehicle> findAllByModel(String model) {
        return repository.findByModel(model);
    }

    @Override
    public Mono<Vehicle> create(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    public Mono<Vehicle> update(Long id, Mono<Vehicle> vehicle) {
        return this.repository.findById(id)
                .flatMap(v -> vehicle.map(vv -> {
                    v.setModel(vv.getModel());
                    v.setCubicCentimeters(vv.getCubicCentimeters());
                    v.setReleased(vv.getReleased());
                    return v;
                }))
                .flatMap(this.repository::save);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
