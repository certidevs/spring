package com.example.controller;

import com.example.model.Vehicle;
import com.example.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository repository;

    @GetMapping
    public List<Vehicle> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public void create(@RequestBody Vehicle vehicle){
        System.out.println(vehicle);
    }
}
