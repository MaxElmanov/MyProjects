package com.ibm.mq.jms.logics;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JmsConsumer extends JmsBase {

    private String      queueName   = null;
    private Queue       queue = null;
    private JMSConsumer consumer    = null;

    public JmsConsumer(String host, int port, String channel, String queueManagerName, String queueName)
    {
        super(host, port, channel, queueManagerName);
        this.queueName = queueName;
    }

    public List<String> get()
    {
        List<String> resultSms = new ArrayList<>();

        try (JMSContext context = cf.createContext();){
            queue = context.createQueue(queueName);
//            connection.start();
            consumer = context.createConsumer(queue);

            System.out.println("Getting JMS messages\n");
            Message message = consumer.receiveNoWait();
            while(!Objects.isNull(message)){
                resultSms.add(message.toString());
                message = consumer.receiveNoWait();
            }

        }

        return resultSms;
    }
}
