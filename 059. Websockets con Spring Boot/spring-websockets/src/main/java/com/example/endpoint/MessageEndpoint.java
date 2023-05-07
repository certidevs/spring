package com.example.endpoint;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class MessageEndpoint {

    @MessageMapping("/request")
    @SendTo("/queue/responses")
    public String onMessage(String message){
        System.out.println(message);
        return "server response: " + message;
    }

    @MessageExceptionHandler
    @SendTo("/queue/errors")
    public String onError(Throwable exception){
        return "server exception: " + exception.getMessage();
    }


}
