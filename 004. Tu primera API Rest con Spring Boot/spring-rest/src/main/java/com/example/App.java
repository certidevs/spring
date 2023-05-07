package com.example;

import com.example.model.Manufacturer;
import com.example.repository.ManufacturerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        var repo = context.getBean(ManufacturerRepository.class);
        repo.save(new Manufacturer(null, "manufacturer1", 1000, 1990));
    }

}
