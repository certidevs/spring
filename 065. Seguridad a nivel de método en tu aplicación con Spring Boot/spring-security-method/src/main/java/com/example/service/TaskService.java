package com.example.service;

import com.example.model.Task;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class TaskService {

    List<Task> tasks = List.of(
            new Task(1L, "Task 1", "user1"),
            new Task(2L, "Task 2", "user2"),
            new Task(3L, "Task 3", "user1"),
            new Task(4L, "Task 4", "user4"),
            new Task(5L, "Task 5", "user5")
    );


    @PreAuthorize("#username == authentication.principal.username")
    public List<Task> findByUsername(String username){
        return tasks.stream()
                .filter(task -> task.username().equals(username))
                .toList();
    }

    @PostAuthorize("returnObject.username == authentication.principal.username")
    public Task findFirstByUserName(String username){
        return tasks.stream()
                .filter(task -> task.username().equals(username))
                .findFirst()
                .orElseThrow();
    }

}
