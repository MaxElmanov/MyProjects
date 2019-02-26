package com.test.spring.listQuests;

import com.test.spring.interfaces.Quest;
import org.springframework.stereotype.Component;

@Component
public class QuestTwo implements Quest {

    @Override
    public void make(){
        System.out.println("Knight won a dragon");
    }

}
