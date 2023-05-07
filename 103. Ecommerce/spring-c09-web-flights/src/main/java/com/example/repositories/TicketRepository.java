package com.example.repositories;

import com.example.entities.Flight;
import com.example.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByPriceBetween(double min, double max);
    List<Ticket> findAllByPriceLessThanEqual(double price);
    List<Ticket> findAllByPassengerId(Long id);
    List<Ticket> findAllByFlightId(Long id);
}