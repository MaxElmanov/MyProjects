package com.test.spring.eventsANDlistener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyKnightEventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("Congratulations");
        System.out.println(applicationEvent.toString());
    }
}
