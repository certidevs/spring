package com.example.endpoint;

import com.example.dto.ChatMessage;
import com.example.dto.JoinMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Controller
public class ChatEndpoint {

    @Autowired
    private SimpMessageSendingOperations ops;
    private static final Map<String, Map<String, Object>> sessions = new ConcurrentHashMap<>();


    @MessageMapping("/join")
    public void onJoin(
            @Payload JoinMessage message,
            SimpMessageHeaderAccessor accessor
    ){
        String sessionId = accessor.getSessionId();
        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
        System.out.println(sessionId);
        System.out.println(sessionAttributes);


        String username = message.username();
        sessionAttributes.put("username", username);
        sessions.put(sessionId, sessionAttributes);

        var msg = new ChatMessage(username, username + " joined the chat");
        this.ops.convertAndSend("/topic/responses", msg);

        var usersMsg = new ChatMessage(username, "users:" + getUserNames());
        this.ops.convertAndSend("/topic/users", usersMsg);

    }

    @MessageMapping("/chat")
    @SendTo("/topic/responses")
    public ChatMessage onMessage(
            @Payload ChatMessage message,
            SimpMessageHeaderAccessor accessor
    ){
        System.out.println(message);
        // aquí se podrían hacer distintas comprobaciones antes de enviar el mensaje
        // ...
        return message;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            System.out.println("User Disconnected : " + username);

            var msg = new ChatMessage(username, username + " -- has left the chat -- ");
            ops.convertAndSend("/topic/responses", msg);

            // remove session associated with username
            sessions.remove(headerAccessor.getSessionId());

            var updateUserList = new ChatMessage(username, getUserNames());
            ops.convertAndSend("/topic/users", updateUserList);
        }
    }

    // user1,user2,user3
    private String getUserNames(){
        return sessions.values().stream()
                .map(v -> v.get("username"))
                .map(String::valueOf)
                .collect(Collectors.joining(","));

    }


}
