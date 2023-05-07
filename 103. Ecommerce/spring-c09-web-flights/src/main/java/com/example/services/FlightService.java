package com.example.services;

import com.example.entities.Flight;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface FlightService {

    List<Flight> findAll();
    List<Flight> findAll(Example<Flight> example);

    List<Flight> findAllByAirportFrom(String airport);
    List<Flight> findAllByAirportTo(String airportTo);
    List<Flight> findAllByAirportFromAndAirportTo(String airportFrom, String airportTo);

    Optional<Flight> findById(Long id);
    Flight save(Flight flight);
    void deleteById(Long id);


}
