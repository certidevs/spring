package com.example.repositories;

import com.example.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAllByAirportFrom(String airport);

    List<Flight> findAllByAirportTo(String airportTo);

    List<Flight> findAllByAirportFromAndAirportTo(String airportFrom, String airportTo);

}