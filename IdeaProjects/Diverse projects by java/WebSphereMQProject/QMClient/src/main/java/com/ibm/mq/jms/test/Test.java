package com.ibm.mq.jms.test;

import com.ibm.mq.jms.launcher.Runner;
import com.ibm.mq.jms.timer.Timer;

import java.util.ArrayList;
import java.util.List;

public class Test
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();

        List<String> messages = new ArrayList<>();
        for (int i = 0; i < Runner.getAmountMessageToSend(); i++) {
            messages.add("Hello " + (i + 1));
        }

        timer.start();
        for (int smsNumber = 0; smsNumber < messages.size(); smsNumber++) {
            sent(messages.get(smsNumber));
            timer.stop(smsNumber);
        }
    }

    private static void sent(String s)
    {
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
