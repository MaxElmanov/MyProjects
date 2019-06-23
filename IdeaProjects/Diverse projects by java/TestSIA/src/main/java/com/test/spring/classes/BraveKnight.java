package com.test.spring.classes;

import com.test.spring.interfaces.Helmet;
import com.test.spring.interfaces.Knight;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("braveKnight")
public class BraveKnight implements Knight, BeanNameAware {

    private String nameBean;
    @Value("#{T(java.lang.Math).random() * 25}")
    private int age;
    @Autowired
    private Helmet helmet;

    public BraveKnight() {}

    @Override
    public void doSomething() {
        System.out.println("BEAN NAME:");
        System.out.println("nameBean = " + nameBean);

        System.out.println("HELMET:");
        helmet.protectHead();
        System.out.println("name of helmet = " + ((IronHelmet)helmet).getNameBean());

        System.out.println("Knight age = " + this.age);
    }

    @Override
    public int divideAbyB(int a, int b){
        System.out.println("a/b = "+ a/b);
        return a/b;
    }

    public Helmet getHelmet() {
        return helmet;
    }
    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setBeanName(String s) {
        nameBean = s;
    }
}

