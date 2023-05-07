package com.example;

import com.example.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class EmployeeServiceTests {

    @Autowired
    private EmployeeService service;

    @Test
    void method1AuthenticationException() {
        assertThrows(AuthenticationException.class, () -> {
            service.method1();
        });
    }

    @Test
    @WithMockUser(authorities = "read")
    void method1OK() {
        String result = service.method1();
        assertEquals("method1", result);
    }

    @Test
    @WithMockUser(authorities = "read")
    void method2() {
        String result = service.method2();
        assertEquals("method2", result);
    }

    @Test
    @WithMockUser(authorities = "read")
    void method3AccessDeniedException() {
        String result = service.method3();
        assertThrows(AccessDeniedException.class, () -> {
            service.method3();
        });
    }

    @Test
    @WithMockUser(authorities = "write")
    void method3OK() {
        String result = service.method3();
        assertEquals("method3", result);
    }



}
