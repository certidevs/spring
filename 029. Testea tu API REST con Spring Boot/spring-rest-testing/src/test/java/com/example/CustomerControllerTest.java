package com.example;

import com.example.customer.BadRequestException;
import com.example.customer.Customer;
import com.example.customer.CustomerController;
import com.example.customer.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    void findAllSuccess() throws Exception {

        List<Customer> customers = List.of(
                new Customer(1L, "customer1", 30),
                new Customer(2L, "customer2", 40),
                new Customer(3L, "customer3", 50)
        );
        when(customerRepository.findAll()).thenReturn(customers);

        mockMvc.perform(
                    get("/api/customers").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("customer3")));
    }

    @Test
    void findAllNotFound() throws Exception {

        List<Customer> customers = List.of();
        when(customerRepository.findAll()).thenReturn(customers);

        mockMvc.perform(
                        get("/api/customers").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void findByIdOk() throws Exception {
        Customer customer = new Customer(1L, "customer1", 35);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        mockMvc.perform(
                        get("/api/customers/1").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("customer1")))
                .andExpect(jsonPath("$.age", is(35)));


    }

    @Test
    void findByIdNotFound() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(
                        get("/api/customers/1").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void createOk() throws Exception {
        Customer customer = new Customer(null, "customer1", 35);
        Customer customerDB = new Customer(1L, "customer1", 35);

        when(customerRepository.save(any())).thenReturn(customerDB);

        mockMvc.perform(post("/api/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(customer))

        ).andExpect(status().isOk())
        .andExpect(jsonPath("$", notNullValue()))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("customer1")))
        .andExpect(jsonPath("$.age", is(35)));

    }

    @Test
    void createBadRequest() throws Exception {
        Customer customer = new Customer(1L, "customer1", 35);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(customer))

                ).andExpect(status().isBadRequest());
    }
    @Test
    void updateOk() throws Exception {
        Customer customer = new Customer(1L, "customer1", 35);
        Customer customerUpdated = new Customer(1L, "customer1 edited", 35);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(customerUpdated);

        mockMvc.perform(put("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(customer))

                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("customer1 edited")))
                .andExpect(jsonPath("$.age", is(35)));

    }

    @Test
    void updateBadRequestException() throws Exception {
        Customer customer = new Customer(null, "customer1", 35);

        mockMvc.perform(put("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(customer))

        ).andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException))
        .andExpect(result -> assertEquals("id customer can not be null", result.getResolvedException().getMessage()));
    }
    @Test
    void updateNotFound() throws Exception {

    }

    @Test
    void deleteByIdNoContentSuccess() throws Exception {

    }

    @Test
    void deleteByIdNotFound() throws Exception {

    }

}
