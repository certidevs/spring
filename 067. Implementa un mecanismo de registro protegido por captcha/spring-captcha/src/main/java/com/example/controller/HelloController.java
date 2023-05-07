package com.example.controller;

import com.example.captcha.RequiresCaptcha;
import com.example.dto.HelloDTO;
import com.example.dto.HelloResponseDTO;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping
    @RequiresCaptcha
    public HelloResponseDTO hello(@RequestBody HelloDTO hello){
        return new HelloResponseDTO("Hello from server user: " + hello.name());
    }

}
