package ru.spring.security.project.servies;

import org.springframework.security.access.annotation.Secured;

public interface ProcessInterface {

    @Secured("ROLE_USER")
    String getMessage();

}
