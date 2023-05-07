package com.example.services;

import com.example.entities.Flight;
import com.example.entities.Ticket;
import com.example.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final TicketService ticketService;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findAll(Example<Flight> example) {
        return flightRepository.findAll(example);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public List<Flight> findAllByAirportFrom(String airport) {
        return flightRepository.findAllByAirportFrom(airport);
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteById(Long id) {
        // desasociar tickets
//        List<Ticket> tickets = ticketService.findAllByFlightId(id);
//        for (Ticket ticket : tickets)
//            ticket.setFlight(null); // desasociar vuelo
//
//        ticketService.saveAll(tickets);

//        ticketService.findAllByFlightId(id).forEach(ticket -> System.out.println(ticket)); // lambda
//        ticketService.findAllByFlightId(id).forEach(System.out::println); // method reference //abreviación de una lambda
//        ticketService.findAllByFlightId(id).forEach(ticket -> ticket.setFlight(null));

//        List<Ticket> tickets = ticketService.findAllByFlightId(id);
//        tickets.forEach(ticket -> ticket.setFlight(null));
//        ticketService.saveAll(tickets);

        ticketService.findAllByFlightId(id).forEach(ticket -> {
            ticket.setFlight(null);
            ticketService.save(ticket);
        });

        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> findAllByAirportTo(String airportTo) {
        return flightRepository.findAllByAirportTo(airportTo);
    }

    @Override
    public List<Flight> findAllByAirportFromAndAirportTo(String airportFrom, String airportTo) {
        return flightRepository.findAllByAirportFromAndAirportTo(airportFrom, airportTo);
    }
}
