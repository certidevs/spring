package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.ldap.userdetails.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(Authentication authentication){
        return "Hello, " + authentication.getName();
    }

    @GetMapping("/user")
    public String hello(@AuthenticationPrincipal Person person){
        return "Hello, " + person.getGivenName();
    }

}
