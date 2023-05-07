package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello(){
        return "Hello unsecured";
    }

    @GetMapping("/api/hello-secured")
    public Map<String, Object> whoami(Authentication authentication) {
        return Collections.singletonMap("name", authentication.getName());
    }

}
