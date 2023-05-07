package com.example;

import com.example.model.Task;
import com.example.repository.TaskRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        var repo = ctx.getBean(TaskRepository.class);
        repo.saveAll(List.of(
                new Task(null, "Task 1", "user1"),
                new Task(null, "Task 2", "user2"),
                new Task(null, "Task 3", "user1"),
                new Task(null, "Task 4", "user2")
        ));
    }

}
