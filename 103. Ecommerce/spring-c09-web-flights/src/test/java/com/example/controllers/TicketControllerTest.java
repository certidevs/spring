package com.example.controllers;

import com.example.entities.Flight;
import com.example.entities.Ticket;
import com.example.services.FileService;
import com.example.services.FlightService;
import com.example.services.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
@DisplayName("Controlador de entidad Ticket")
class TicketControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    TicketService ticketService;
    @MockBean
    FlightService flightService;
    @MockBean
    FileService fileService;

    @Test
    @WithMockUser
    @DisplayName("Buscar todos los tickets")
    void findAllTest() throws Exception {

        // configurar mock
        when(ticketService.findAll()).thenReturn(new ArrayList<>(List.of(
                new Ticket(),
                new Ticket()
        )));

        // ejecutar petición /tickets
        mvc.perform(get("/tickets")).andExpectAll(
                status().isOk(),
                content().contentType("text/html;charset=UTF-8"),
                model().attributeExists("tickets"),
                model().attribute("tickets", hasSize(2)),
                view().name("ticket/ticket-list")
        );

        // verificaciones
        verify(ticketService).findAll();
    }

    @Test
    @WithMockUser
    @DisplayName("Mostrar formulario creación de nuevo ticket")
    void showCreateFormTest() throws Exception {

        List<Flight> flights = new ArrayList<>(List.of(
                new Flight(),
                new Flight()
        ));
        when(flightService.findAll()).thenReturn(flights);

        mvc.perform(get("/tickets/create")).andExpectAll(
                status().isOk(),
                content().contentType("text/html;charset=UTF-8"),
                model().attributeExists("ticket"),
                model().attributeExists("flights"),
                model().attribute("flights", hasSize(2)),
                view().name("ticket/ticket-form")
        );
        verify(flightService).findAll();


    }

}