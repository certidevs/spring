package com.example.event;

import org.springframework.context.ApplicationEvent;

public class EmployeeEvent extends ApplicationEvent {
    private String message;

    public EmployeeEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
