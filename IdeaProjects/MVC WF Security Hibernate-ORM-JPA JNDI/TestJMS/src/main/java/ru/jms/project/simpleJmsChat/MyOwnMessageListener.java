package ru.jms.project.simpleJmsChat;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyOwnMessageListener implements MessageListener {

    public void onMessage(Message message) {
        try {
            TextMessage text = (TextMessage) message;
            System.out.println(text.getText());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
