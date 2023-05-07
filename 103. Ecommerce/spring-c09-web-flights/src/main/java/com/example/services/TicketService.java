package com.example.services;

import com.example.entities.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> findAll();
    Optional<Ticket> findById(Long id);
    List<Ticket> findAllByPriceBetween(double min, double max);
    List<Ticket> findAllByPriceLessThanEqual(double price);
    Ticket save(Ticket ticket);
    void deleteById(Long id);
    List<Ticket> findAllByPassengerId(Long id);
    void buyTicketForCurrentUser(Long ticketId);
    List<Ticket> findAllByFlightId(Long id);
    List<Ticket> saveAll(List<Ticket> tickets);
}
