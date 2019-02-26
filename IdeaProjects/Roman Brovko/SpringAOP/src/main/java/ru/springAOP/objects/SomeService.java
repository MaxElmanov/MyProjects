package ru.springAOP.objects;

import org.springframework.stereotype.Component;

@Component
public class SomeService {

    public int getIntValue(int a, int b){
        return a/b;
    }

    public double getDoubleValue(){
        return 5.6;
    }
}
