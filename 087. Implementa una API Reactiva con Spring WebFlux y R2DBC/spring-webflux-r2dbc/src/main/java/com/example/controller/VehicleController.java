package com.example.controller;

import com.example.model.Vehicle;
import com.example.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public Flux<Vehicle> findAll() {
        return this.service.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Vehicle>> findById(@PathVariable Long id) {
        return this.service.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/released/{released}")
    public Flux<Vehicle> findAllByReleased(@PathVariable Integer released) {
        return this.service.findAllByReleased(released);
    }

    @PostMapping
    public Mono<ResponseEntity<Vehicle>> create(@RequestBody Vehicle vehicle) {
        return this.service.save(vehicle).map(ResponseEntity::ok);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Vehicle>> update(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return this.service.findById(id)
                .map(v -> {
                    v.setModel(vehicle.getModel());
                    v.setCubicCentimeters(vehicle.getCubicCentimeters());
                    v.setReleased(vehicle.getReleased());
                    return v;
                }).flatMap(this.service::save)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable Long id) {
        return service.findById(id)
                .flatMap(vehicle ->
                        service.delete(vehicle)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
