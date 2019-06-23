package com.test.spring;

import com.test.spring.classes.BraveKnight;
import com.test.spring.interfaces.Knight;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/knight.xml");

        Knight knight = (Knight) context.getBean("braveKnight");

//        knight.doSomething();
        knight.divideAbyB(16,2);
    }

}
