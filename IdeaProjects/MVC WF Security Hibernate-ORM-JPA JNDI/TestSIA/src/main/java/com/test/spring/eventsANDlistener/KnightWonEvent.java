package com.test.spring.eventsANDlistener;

import org.springframework.context.ApplicationEvent;

public class KnightWonEvent extends ApplicationEvent {

    public KnightWonEvent(Object source) {
        super(source);
    }
}
