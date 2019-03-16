package com.javarzn.donework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CalculatorTest {
    public CalculatorTest() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method m: methods) {
            if (m.isAnnotationPresent(MyTest.class)) {
                m.invoke(this);
            }
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        new CalculatorTest();
    }

    public void noTestVoid() {
        System.out.println("Ничего не запустили");
    }

    @MyTest
    public void testPlus() {
        System.out.println("Запустили тест сложения!");
//        com.javarzn.donework.Calculator calculator = new com.javarzn.donework.Calculator();
//        calculator.plus(2, 2);
    }

    @MyTest
    public void testMinus() {
        System.out.println("Запустили тест вычитания!");
    }

    @MyTest
    public void testDelit() {
        System.out.println("Запустили тест деления!");
    }

    @MyTest
    public void testUmnuzhit() {
        System.out.println("Запустили тест умножения!");
    }
}
