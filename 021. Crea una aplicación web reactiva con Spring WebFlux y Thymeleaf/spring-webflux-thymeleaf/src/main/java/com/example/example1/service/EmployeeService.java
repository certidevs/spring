package com.example.example1.service;

import com.example.example1.model.Employee;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class EmployeeService {


    public Flux<Employee> findAll(){
        Random rand = new Random();
        return Flux.fromStream(
                Stream.generate(
                        () -> new Employee(rand.nextLong(), "emp" + rand.nextLong(), 60000.0)
                ).limit(30000)
        );
    }
}
