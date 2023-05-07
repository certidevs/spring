package com.example.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.websocket.OnMessage;

@Controller
public class ScheduledEndpoint {

    @Autowired
    private MessageSendingOperations<String> ops;

     @Scheduled(fixedRate = 5000)
     public void onSchedule(){
         this.ops.convertAndSend("/topic/periodic", "server periodic message");
     }

}
