package ru.spring.security.project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.security.project.servies.ProcessIml;
import ru.spring.security.project.servies.ProcessInterface;

import java.awt.*;
import java.security.Principal;

@Controller
public class MyController {
    public static final Logger log = LogManager.getLogger(MyController.class);

    @Autowired
    private ProcessInterface process;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(Model model){
        printPrincipal();

        return "/content/user";
    }

    private void printPrincipal() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info(userDetails.getUsername());
        log.info(userDetails.getPassword());

        for (GrantedAuthority auth : userDetails.getAuthorities()) {
            log.info(auth.getAuthority());
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model){
        return "/content/admin";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false)String error, ModelAndView model){
        if(error != null) {
            model.addObject("error", "User name or password is invalid");
        }

        log.info(process.getMessage());

        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(Principal user, Model model){

        if (user != null){
            model.addAttribute("errorMsg", user.getName() + " has not access to this page");
        }
        else{
            model.addAttribute("errorMsg", "You have not access to this page");
        }

        return "/content/accessDenied";
    }
}
