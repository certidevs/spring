package com.example.controller;

import com.example.messaging.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private Publisher publisher;

    @GetMapping("/message/{message}")
    public String sendMessage(@PathVariable String message){
        // enviar el mensaje a redis
        publisher.publishMessage(message);
        return "Message sent to redis";
    }
}
