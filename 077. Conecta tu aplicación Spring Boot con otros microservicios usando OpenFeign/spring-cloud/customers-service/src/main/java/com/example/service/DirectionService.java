package com.example.service;

import com.example.dto.DirectionDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DirectionService {

    @Autowired
    private DirectionsFeignClient client;

    public List<DirectionDTO> findAllDirections() {
        return client.fetchDirections();
    }

    // CIRCUIT BREAKER
    @CircuitBreaker(name = "directionsCB", fallbackMethod = "directionsFallback")
    public String helloFromDirections() {
        return client.fetchHello();
    }
    private String directionsFallback(CallNotPermittedException e) {
        return "Fallback: " + e.getMessage();
    }


    // TIME LIMITER
    @Bulkhead(name = "directionsTL", type = Bulkhead.Type.THREADPOOL)
    @TimeLimiter(name = "directionsTL")
    public CompletableFuture<List<DirectionDTO>> findSlow() {
        return CompletableFuture.completedFuture(client.fetchDirections());
    }


}
