package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Controller {

    @GetMapping("/product")
    public String hello(Principal principal){
        return "Hello " + principal.getName();
    }

    @GetMapping("/product/hello")
    public String hello2(Principal principal){
        return "Hello " + principal.getName();
    }
}
