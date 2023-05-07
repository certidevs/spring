package com.example.controller;

import com.example.dto.DirectionDTO;
import com.example.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/customers-service")
public class DirectionsController {

    @Autowired
    private DirectionService service;

    @RequestMapping("/circuit-error")
    public String hello() {
        return service.helloFromDirections();
    }

    @RequestMapping("/timelimit-error")
    public List<DirectionDTO> findAll() throws ExecutionException, InterruptedException {
        return service.findSlow().get();
    }

}
