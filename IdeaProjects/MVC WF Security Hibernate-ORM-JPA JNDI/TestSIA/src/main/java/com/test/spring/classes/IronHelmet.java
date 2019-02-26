package com.test.spring.classes;

import com.test.spring.interfaces.Helmet;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IronHelmet implements Helmet, BeanNameAware{

    private String nameBean;

    @Override
    public void protectHead() {
        System.out.println("head of knight is protected");
    }

    @Override
    public void setBeanName(String s) {
        nameBean = s;
    }

    public String getNameBean() {
        return nameBean;
    }
}
