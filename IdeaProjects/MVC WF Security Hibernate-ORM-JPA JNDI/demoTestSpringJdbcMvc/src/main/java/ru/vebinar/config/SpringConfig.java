package ru.vebinar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vebinar.service.UserServiceImpl;

@Configuration
public class SpringConfig {

    @Bean
    public UserServiceImpl getUserServiceImpl(){
        return new UserServiceImpl();
    }
}