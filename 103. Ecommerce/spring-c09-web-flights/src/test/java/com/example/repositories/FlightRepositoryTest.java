package com.example.repositories;


import com.example.entities.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
https://docs.spring.io/spring-data/jpa/docs/3.1.0-M2/reference/html/#query-by-example
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @BeforeEach
    void beforeEach() {
        Flight f1 = new Flight(null, "AIRBUS 7000", "MAD", "HUG", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(3), 200, null);
        Flight f2 = new Flight(null, "AIRBUS 7000", "BCN", "LIS", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(3), 200, null);
        Flight f3 = new Flight(null, "AIRBUS 7000", "MAD", "CHK", LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(3), 200, null);
        flightRepository.saveAll(List.of(f1, f2, f3));
    }

    @AfterEach
    void afterEach() {
        flightRepository.deleteAll();
    }

    @Test
    void findAllFilter() {

//        Flight flight = new Flight(null, null, "MAD", null, null, null, null, null);
//        Flight flight = new Flight(null, null, "MAD", "CHK", null, null, null, null);
        Flight flight = new Flight();
        Example<Flight> example = Example.of(flight);

        List<Flight> flights = flightRepository.findAll(example);
        System.out.println(flights);

    }
}