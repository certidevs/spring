package com.example;

import com.example.customer.BadRequestException;
import com.example.customer.Customer;
import com.example.customer.CustomerController;
import com.example.customer.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("customers-{method-name}",  // alwaysDo opcional
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    public void findAll() throws Exception {
        List<Customer> customers = List.of(
                new Customer(1L, "cust1", 34),
                new Customer(2L, "cust2", 34),
                new Customer(3L, "cust3", 34)
        );

        when(customerRepository.findAll()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("cust3"))).andDo(document("customers/findAll"));
    }

    @Test
    public void findById() throws Exception {
        Customer cust1 = new Customer(1L, "cust1", 34);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(cust1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("cust1")))
                .andExpect(jsonPath("$.age", is(34)))
                .andDo(document("customers/findById", responseFields(
                        fieldWithPath("id").description("Long Primary Key"),
                        fieldWithPath("name").description("Full name (two words)"),
                        fieldWithPath("age").description("Integer age (18-60)")
                        )));
    }

    @Test
    public void create() throws Exception {
        Customer customer = new Customer(null, "cust1", 34);
        Customer savedCustomer = new Customer(1L, "cust1", 34);

        when(customerRepository.save(any())).thenReturn(savedCustomer);

        var request = MockMvcRequestBuilders.post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(customer));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("cust1")));
    }

    @Test
    public void updateSuccess() throws Exception {
        Customer customer = new Customer(1L, "cust1", 34);
        Customer updatedCustomer = new Customer(1L, "cust1 edited", 34);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(updatedCustomer);

        var mockRequest = MockMvcRequestBuilders.put("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedCustomer));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("cust1 edited")));
    }

    @Test
    public void updateNullId() throws Exception {
        Customer customer = new Customer(null, "cust1 edited", 34);

        var mockRequest = MockMvcRequestBuilders.put("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(customer));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof BadRequestException))
                .andExpect(result ->
                        assertEquals("id customer can not be null", result.getResolvedException().getMessage()));
    }

    @Test
    public void updateNotFound() throws Exception {
        Customer customer = new Customer(1L, "cust1 edited", 34);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());

        var mockRequest = MockMvcRequestBuilders.put("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(customer));

        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteByIdSuccess() throws Exception {
        when(customerRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteByIdNotFound() throws Exception {
        when(customerRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
