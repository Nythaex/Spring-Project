package com.example.demo.utils.event;

import org.springframework.context.ApplicationEvent;

public class InitEvent extends ApplicationEvent {
    public InitEvent(Object source) {
        super(source);
    }
}
