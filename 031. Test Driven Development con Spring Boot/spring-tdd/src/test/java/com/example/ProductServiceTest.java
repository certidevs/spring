package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    // System Under Test
    ProductService service;

    @Mock
    ProductRepository repository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new ProductService(repository);
    }

    @Test
    void findAll() {

        when(repository.findAll())
                .thenReturn(List.of(
                        new Product(1L, "Product 1", 100.0),
                        new Product(2L, "Product 2", 100.0)
                ));

        var products = service.findAll();
        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId());
    }

    @Test
    void calculateStock() {


    }

    @Test
    void createSuccess() {
    }

    @Test
    void createFalse() {
    }
}
