package com.example;

import com.example.entities.Flight;


import com.example.entities.Passenger;
import com.example.entities.Ticket;
import com.example.repositories.FlightRepository;
import com.example.repositories.PassengerRepository;
import com.example.repositories.TicketRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        var context = SpringApplication.run(App.class, args);

        var flightRepo = context.getBean(FlightRepository.class);

        Flight f1 = new Flight(null,
                "AIRBUS 7000",
                "MAD",
                "HUG",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(3),
                200, null);

        Flight f2 = new Flight(null,
                "AIRBUS 7000",
                "BCN",
                "LIS",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(3),
                200, null);

        Flight f3 = new Flight(null,
                "AIRBUS 7000",
                "MAD",
                "CHK",
                LocalDateTime.now().plusDays(3),
                LocalDateTime.now().plusDays(3).plusHours(3),
                200, null);

        flightRepo.saveAll(List.of(f1, f2, f3));



        var passengerRepo = context.getBean(PassengerRepository.class);
        var encoder = context.getBean(PasswordEncoder.class);
        Passenger psg1 = new Passenger(null, "psg1@gmail.com", encoder.encode("admin"), 300.0, null);
        Passenger psg2 = new Passenger(null, "psg2@gmail.com", encoder.encode("admin"), 300.0, null);
        passengerRepo.saveAll(List.of(psg1, psg2));

        var ticketRepo = context.getBean(TicketRepository.class);
        Ticket t1 = new Ticket(null, "A1", 58.8, false, "imagen1.jpg", f1, psg1);
        Ticket t2 = new Ticket(null, "A2", 58.8, false, "imagen1.jpg", f2, psg1);
        Ticket t3 = new Ticket(null, "A3", 58.8, false, "imagen1.jpg", f3, psg1);
        Ticket t4 = new Ticket(null, "B1", 58.8, false, "imagen2.jpg", f1, psg2);
        Ticket t5 = new Ticket(null, "C1", 150.0, false, "imagen2.jpg", f1, null);
        Ticket t6 = new Ticket(null, "D1", 150.0, false, "imagen2.jpg", f2, null);
        Ticket t7 = new Ticket(null, "E1", 150.0, false, "imagen3.jpg", f3, null);
        Ticket t8 = new Ticket(null, "F1", 150.0, false, "imagen3.jpg", f1, null);
        ticketRepo.saveAll(List.of(t1, t2, t3, t4, t5, t6, t7, t8));
    }

}
