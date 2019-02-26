package ru.vebinar.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vebinar.service.UserServiceImpl;

@Controller
public class UserController {

    @Qualifier(value = "service")
    private UserServiceImpl userService;

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/users")
    public String users(Model m){
        m.addAttribute("listUsers", userService.findAll());
        return "users";
    }
}
