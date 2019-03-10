package ru.spring.security.project.servies;

import org.springframework.stereotype.Service;

@Service("process")
public class ProcessIml implements ProcessInterface{

    public String getMessage() {
        return "my message";
    }

}
