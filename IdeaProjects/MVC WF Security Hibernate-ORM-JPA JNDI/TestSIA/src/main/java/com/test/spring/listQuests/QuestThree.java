package com.test.spring.listQuests;

import com.test.spring.interfaces.Quest;
import org.springframework.stereotype.Component;

@Component
public class QuestThree implements Quest {

    @Override
    public void make() {
        System.out.println("Save a cat from die");
    }
}
