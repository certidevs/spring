package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@Rollback(value = false) // por defecto es true lo que significa que revierte todos los cambios al final de cada test

// Interesante mantener el datasource original. Se podría añadir Testcontainers para levantar un contenedor docker con mysql
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    // SUT - System Under Test
    @Autowired
    private OrderRepository repository;

    @BeforeEach
    void setUp() {

    }

    private Order insertDemoOrder(Order order){
        entityManager.persist(order);
        entityManager.flush();
        return order;
    }

    @Test
    @Sql("orders.sql")
    void findAllWithSql() {
        List<Order> orders = repository.findAll();
        assertEquals(2, orders.size());
    }

    @Test
    @SqlGroup( value = {
            @Sql("orders.sql"),
            @Sql("customers.sql")}
    )
    void findAllWithSqlGroup() {
        List<Order> orders = repository.findAll();
        assertEquals(4, orders.size());
    }

    @Test
    void findAllByClient() {
        insertDemoOrder(new Order(null, "customer1", 33.55, LocalDate.of(2022,1,1)));
        insertDemoOrder(new Order(null, "customer2", 33.55, LocalDate.of(2022,1,1)));
        insertDemoOrder(new Order(null, "customer2", 33.55, LocalDate.of(2022,1,1)));

        List<Order> orders = repository.findAllByClient("customer2");
        assertEquals(2, orders.size());
        assertEquals(2L, orders.get(0).getId());
    }

    @Test
    void findAllByTotalBetween() {
        insertDemoOrder(new Order(null, "customer1", 10.0, LocalDate.of(2022,1,1)));
        insertDemoOrder(new Order(null, "customer2", 20.0, LocalDate.of(2022,1,1)));
        insertDemoOrder(new Order(null, "customer3", 30.0, LocalDate.of(2022,1,1)));
        List<Order> orders = repository.findAllByTotalBetween(15.0, 25.0);
        assertEquals(1, orders.size());
        assertEquals(2L, orders.get(0).getId());
        assertEquals("customer2", orders.get(0).getClient());
    }
}