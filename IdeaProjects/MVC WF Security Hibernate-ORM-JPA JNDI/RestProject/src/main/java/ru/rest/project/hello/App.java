package ru.rest.project.hello;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App {

    public static final Logger log = LogManager.getLogger(App.class);

    public static void main( String[] args ) {
        log.info("hey");
        SpringApplication.run(App.class, args);
    }
}
