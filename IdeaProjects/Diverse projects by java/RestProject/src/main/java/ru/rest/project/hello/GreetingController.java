package ru.rest.project.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {

    private String tamplate = "Hello, %s";
    private AtomicInteger counter = new AtomicInteger();

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting getGreeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name){

        Greeting greeting = new Greeting(counter.getAndIncrement(), String.format(tamplate, name));

        return greeting;
    }
}
