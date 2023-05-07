package com.example;

import com.example.model.Vehicle;
import com.example.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner run(VehicleRepository repo){
        return args -> repo.deleteAll()
                .thenMany(Flux.just(
                        new Vehicle(null, "veh1", 1.2, 2019),
                        new Vehicle(null, "veh2", 1.2, 2019),
                        new Vehicle(null, "veh3", 1.2, 2019)
                )).flatMap(repo::save)
                .thenMany(repo.findAll())
                .subscribe(System.out::println);
    }

}
