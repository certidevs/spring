package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/api/method1")
    public String method1() {
        return "method1";
    }
    @GetMapping("/api/method2")
    public String method2() {
        return "method2";
    }

}
