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
    public Flux<Vehicle> findAll(){
        return this.service.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Vehicle>> findById(@PathVariable String id){
        return this.service.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/released/{released}")
    public Flux<Vehicle> findAll(@PathVariable Integer released){
        return this.service.findAllByReleased(released);
    }

    @PostMapping
    public Mono<ResponseEntity<Vehicle>> create(@RequestBody Vehicle vehicle){
        return this.service.save(vehicle)
                .map(ResponseEntity::ok);
    }

    @PutMapping
    public Mono<ResponseEntity<Vehicle>> update(@RequestBody Vehicle vehicle){
        return this.service.findById(vehicle.getId())
                .flatMap(v -> {
                    v.setModel(vehicle.getModel());
                    v.setReleased(vehicle.getReleased());
                    v.setCubicCentimeters(vehicle.getCubicCentimeters());
                    return this.service.save(v);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        return this.service.findById(id)
                .flatMap(v -> this.service.delete(v).then(Mono.just(ResponseEntity.noContent().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
