package com.example.controller;

import com.example.model.Company;
import com.example.model.Vehicle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VehicleController {

    // HTTP Header personalizada
    @GetMapping(value = "/vehicles/{id}", headers = "X-API-V=1")
    public Vehicle findByIdV1(@PathVariable Long id){
        return new Vehicle(1L, "vehicle v1");
    }

    @GetMapping(value = "/vehicles/{id}", headers = "X-API-V=2")
    public Vehicle findByIdV2(@PathVariable Long id){
        return new Vehicle(1L, "vehicle v2");
    }

}
