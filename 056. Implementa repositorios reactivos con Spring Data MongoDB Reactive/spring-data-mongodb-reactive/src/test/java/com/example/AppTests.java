package com.example;

import com.example.model.Vehicle;
import com.example.repository.VehicleRepository;
import com.example.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static java.util.function.Predicate.not;

@SpringBootTest
class AppTests {

    @Autowired
    private VehicleRepository repo;

    @Autowired
    private VehicleService service;


    @BeforeEach
    void setUp() {
        var veh1 = new Vehicle(null, "veh1", 1.2, 2010);
        var veh2 = new Vehicle(null, "veh2", 1.2, 2010);
        var veh3 = new Vehicle(null, "veh3", 1.2, 2010);

//        repo.deleteAll()
//                .thenMany(Flux.just(veh1, veh2, veh3))
//                .flatMap(repo::save)
//                .doOnNext(System.out::println)
//                .blockLast();
        Mono<Void> initDB = repo.deleteAll().thenMany(repo.saveAll(Flux.just(veh1, veh2, veh3))).then();

        StepVerifier.create(initDB).verifyComplete();
    }

    @Test
    void findAll() {

        repo.findAll().as(StepVerifier::create)
                .expectNextMatches(vehicle -> vehicle.getModel().equals("veh1"))
                .expectNextMatches(vehicle -> vehicle.getModel().equals("veh2"))
                .expectNextMatches(vehicle -> vehicle.getModel().equals("veh3"))
                .verifyComplete();

        service.findAll().as(StepVerifier::create)
                .expectNextMatches(vehicle -> vehicle.getModel().equals("VEH1"))
                .expectNextMatches(vehicle -> vehicle.getModel().equals("VEH2"))
                .expectNextMatches(vehicle -> vehicle.getModel().equals("VEH3"))
                .verifyComplete();
    }

    @Test
    void create() {

        var veh4 = new Vehicle(null, "veh4", 1.2, 2010);
        service.create(veh4).as(StepVerifier::create)
                .expectNextMatches(not(vehicle -> vehicle.getId().isEmpty()))
                .verifyComplete();

    }
}
