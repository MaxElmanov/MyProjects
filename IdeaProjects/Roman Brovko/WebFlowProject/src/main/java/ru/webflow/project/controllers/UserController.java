package ru.webflow.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/showPrivateImage", method=RequestMethod.GET)
    public String showPrivateImage(){
        return "privateImagePage";
    }


}
