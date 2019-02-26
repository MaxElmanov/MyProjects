package com.test.spring.helping;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MyBeanCircle implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean");
    }

    public void init(){
        System.out.println("init-method");
    }

    public void Destroy(){
        System.out.println("destroy-method");
    }
}
