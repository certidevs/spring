package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    Producer producer;

    @GetMapping("/send/{phrase}")
    public void send(@PathVariable String phrase) {
        producer.send(phrase);
    }


}
