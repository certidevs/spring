package com.example.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventListener implements ApplicationListener<EmployeeEvent> {

    private static final Logger log = LoggerFactory.getLogger(EmployeeEventListener.class);

    @Override
    public void onApplicationEvent(EmployeeEvent event) {
        log.info(event.getMessage() + " {}", event.getSource());
    }
}
