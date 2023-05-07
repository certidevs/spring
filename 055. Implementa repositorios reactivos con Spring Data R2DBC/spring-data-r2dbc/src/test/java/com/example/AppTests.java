package com.example;

import com.example.model.Vehicle;
import com.example.repository.VehicleRepository;
import com.example.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
2 enfoques para trabajar con base de datos a nivel reactivo:

1. repositorios
2. R2dbcEntityOperations

 */
@SpringBootTest
class AppTests {

    @Value("classpath:schema.sql")
    private Resource schema;
    @Value("classpath:data.sql")
    private Resource data;

    @Autowired
    private R2dbcEntityTemplate template;

    @Autowired
    private VehicleRepository repository;

    @Autowired
    private VehicleService service;

    @BeforeEach
    void setUp() throws IOException {

        String schemaSQL = StreamUtils.copyToString(schema.getInputStream(), StandardCharsets.UTF_8);
        String dataSQL = StreamUtils.copyToString(data.getInputStream(), StandardCharsets.UTF_8);

        this.template.getDatabaseClient()
                .sql(schemaSQL)
                .then()
                .then(this.template.getDatabaseClient().sql(dataSQL).then())
                .block();
    }

    @Test
    void findAll() {

        repository.findAll().as(StepVerifier::create)
                .expectNextMatches(vehicle -> vehicle.getModel().equals("vehicle1"))
                .expectNextMatches(vehicle -> vehicle.getModel().equals("vehicle2"))
                .verifyComplete();

        service.findAll().as(StepVerifier::create)
                .expectNextMatches(vehicle -> vehicle.getModel().equals("VEHICLE1"))
                .expectNextMatches(vehicle -> vehicle.getModel().equals("VEHICLE2"))
                .verifyComplete();
    }

    @Test
    void findById() {

        repository.findById(2L).as(StepVerifier::create)
                .expectNextMatches(vehicle -> vehicle.getId().equals(2L))
                .verifyComplete();
    }

    @Test
    void create() {

        var vehicle = new Vehicle(null, "vehicle3", 3.0, 2010);

        service.create(vehicle)
                .as(StepVerifier::create)
                .expectNextMatches(v -> v.getId().equals(3L))
                .verifyComplete();

        repository.count().as(StepVerifier::create)
                .expectNext(3L)
                .verifyComplete();
    }

    @Test
    void update() {

        var vehicle = new Vehicle(null, "vehicle1 edited", 3.0, 2010);

        service.update(1L, Mono.just(vehicle))
                .as(StepVerifier::create)
                .expectNextMatches(v -> v.getId().equals(1L) && v.getModel().equals("vehicle1 edited"))
                .verifyComplete();
    }

    @Test
    void updateNotFound() {

        var vehicle = new Vehicle(null, "vehicle1 edited", 3.0, 2010);

        service.update(999L, Mono.just(vehicle))
                .as(StepVerifier::create)
                .expectNextCount(0).verifyComplete();

    }

    @Test
    void deleteById() {

        service.deleteById(1L)
                .as(StepVerifier::create)
                .verifyComplete();

        repository.count().as(StepVerifier::create)
                .expectNext(1L)
                .verifyComplete();

    }
}
