package com.example;

import com.example.model.Vehicle;
import com.example.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class App {

    @Value("classpath:schema.sql")
    private Resource schema;

    @Autowired
    private R2dbcEntityTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner run(VehicleRepository repo) throws IOException {
        String schemaSQL = StreamUtils.copyToString(schema.getInputStream(), StandardCharsets.UTF_8);
        this.template.getDatabaseClient().sql(schemaSQL).then().block();

        return args ->
            repo.deleteAll().thenMany(Flux.just(
                    new Vehicle(null, "Ferrari", 5999.0, 2019),
                    new Vehicle(null, "Lamborghini", 6499.0, 2019),
                    new Vehicle(null, "Porsche", 4999.0, 2019)
            )).flatMap(repo::save).thenMany(repo.findAll()).subscribe(System.out::println);

    }

}
