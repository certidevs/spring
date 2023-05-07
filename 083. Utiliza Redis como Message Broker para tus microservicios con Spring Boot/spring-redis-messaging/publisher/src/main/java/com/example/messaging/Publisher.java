package com.example.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    private static final Logger logger = LoggerFactory.getLogger(Publisher.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ChannelTopic topic;

    public void publishMessage(String message){
        redisTemplate.convertAndSend(topic.getTopic(), message);
        logger.info("Message published to topic - " + topic.getTopic() + " : " + message);
    }
    
}
