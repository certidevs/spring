package com.example.controller;

import com.example.model.Task;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/method1/{id}")
    public Task findByIdPost(@PathVariable Long id){
        return service.findTaskPost(id);
    }

    @GetMapping("/method2/{id}")
    public Task findByIdPre(@PathVariable Long id){
        return service.findTaskPre(id);
    }

    @GetMapping("/method3/{title}")
    public List<Task> findByTitleContains(@PathVariable String title){
        return service.filterByTitleContains(title);
    }
    @GetMapping("/method4/{title}")
    public List<Task> findByTitleContainsQuery(@PathVariable String title){
        return service.filterByTitleContainsQuery(title);
    }
}
