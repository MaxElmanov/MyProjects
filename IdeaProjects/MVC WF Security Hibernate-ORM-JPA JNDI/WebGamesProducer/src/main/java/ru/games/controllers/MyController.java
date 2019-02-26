package ru.games.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.games.bean.Genofond;
import ru.games.logic.LogicGeneration;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private LogicGeneration logic;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/tanks")
    public String tanks(){
        return "tanks";
    }

    @RequestMapping(value = "/helicopterAndCats")
    public String helicopterAndCats(){
        return "helicopterAndCats";
    }

    @RequestMapping(value = "/function")
    public String function(Model model){
        model.addAttribute("genofond", new Genofond());

        return "function";
    }

    @RequestMapping(value = "/generateGeneration")
    public String function(@ModelAttribute("genofond") Genofond genofond, Model m){

        List<Double> listResults = logic.generateGeneration(genofond);

        m.addAttribute("listResults", listResults);

        return "function";
    }

}
