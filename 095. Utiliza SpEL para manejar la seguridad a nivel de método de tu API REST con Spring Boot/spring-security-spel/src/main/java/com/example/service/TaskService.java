package com.example.service;

import com.example.model.Task;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @PostAuthorize("hasPermission(returnObject, 'ROLE_ADMIN')")
    public Task findTaskPost(Long id){
        return taskRepository.findById(id).stream()
                .findFirst()
                .orElseThrow();
    }

    @PreAuthorize("hasPermission(#id, 'com.example.model.Task', 'ROLE_ADMIN')")
    public Task findTaskPre(Long id){
        return taskRepository.findById(id).stream()
                .findFirst()
                .orElseThrow();
    }


    public List<Task> filterByTitleContains(String title){
        return taskRepository.findByTitleContains(title);
    }
    public List<Task> filterByTitleContainsQuery(String title){
        return taskRepository.findByTitleContainsQuery(title);
    }

}
