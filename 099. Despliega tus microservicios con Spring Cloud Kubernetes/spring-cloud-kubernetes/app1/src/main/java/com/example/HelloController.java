package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private ClientConfig config;

    @GetMapping
    public String sayHello() {
        return config.getMessage1() + " " + config.getMessage2();
    }

}
