package com.example;

import com.example.model.Task;
import com.example.service.EmployeeService;
import com.example.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TaskServiceTests {

    @Autowired
    private TaskService service;

    @Test
    void findByUsernameException() {
        assertThrows(AuthenticationException.class, () -> {
            service.findByUsername("user1");
        });
    }
    @Test
    @WithMockUser(username = "user1")
    void findByUsernameOK() {
        List<Task> tasks = service.findByUsername("user1");
        assertEquals(2, tasks.size());
    }

    @Test
    @WithMockUser(username = "user1")
    void findFirstByUserName() {
        assertThrows(AccessDeniedException.class, () -> {
            service.findFirstByUserName("user2");
        });
    }
}
