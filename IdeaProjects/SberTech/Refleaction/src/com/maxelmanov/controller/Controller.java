package com.maxelmanov.controller;

import com.maxelmanov.animals.Tom;
import java.lang.reflect.Field;

public class Controller {
    public static void getAllPublicFields(Class c){
        Class enflector = c;
        Field[] fields = enflector.getFields();

        for (Field field : fields){
            System.out.println(field.getName());
        }
    }

    public static void getAllDeclaredFields(Class c){
        Class enflector = c;

        while(!enflector.equals(Object.class)) {
            Field[] fields = enflector.getDeclaredFields();

            for (Field field : fields) {
                System.out.println(field.getName());
            }

            enflector = enflector.getSuperclass();
        }
    }
}
