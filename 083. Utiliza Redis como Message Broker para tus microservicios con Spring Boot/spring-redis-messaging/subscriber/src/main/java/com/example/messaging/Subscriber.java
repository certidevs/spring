package com.example.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

//@Component
public class Subscriber implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(Subscriber.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(pattern);
        logger.info("Received message from topic - " + topic + " : " + new String(message.getBody()));
    }
}
