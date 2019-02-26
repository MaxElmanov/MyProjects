package com.maxelmanov.main;

import com.maxelmanov.animals.Tom;
import com.maxelmanov.controller.Controller;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("PUBLIC FIELDS");
        Controller.getAllPublicFields(Tom.class);

        System.out.println();

        System.out.println("DECLARED FIELDS");
        Controller.getAllDeclaredFields(Tom.class);
    }
}
