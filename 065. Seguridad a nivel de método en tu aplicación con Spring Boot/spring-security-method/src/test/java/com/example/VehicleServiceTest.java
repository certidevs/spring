package com.example;

import com.example.model.Task;
import com.example.model.Vehicle;
import com.example.service.TaskService;
import com.example.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class VehicleServiceTest {

    @Autowired
    private VehicleService service;

    @Test
    void method1() {
        assertThrows(AuthenticationException.class, () -> {
            service.method1();
        });
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void adminRole() {
        assertEquals("method1", service.method1());
        assertEquals("method2", service.method2());
        assertEquals("method3", service.method3());
    }

    @Test
    @WithMockUser(roles = "USER")
    void userRole() {
        assertThrows(AccessDeniedException.class, () -> {
            service.method1();
        });
        assertThrows(AccessDeniedException.class, () -> {
            service.method2();
        });
        assertEquals("method3", service.method3());
    }

    @Test
    @WithMockUser(username = "user2")
    void method4() {
        List<Vehicle> vehicles = service.method4(new ArrayList<>(List.of(
                new Vehicle(1L, "ford", "user1"),
                new Vehicle(2L, "honda", "user2"),
                new Vehicle(3L, "honda", "user1")
        )));
        assertEquals(1, vehicles.size());
    }
}
