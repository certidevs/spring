package com.example.endpoint;

import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SubscribeEndpoint {

    @SubscribeMapping("/subscribe")
    public String onSubscribe(){
        return "new connection";
    }
}
