package com.ibm.mq.jms.logics;

import javax.jms.*;

public class JmsProducer extends JmsBase {

    private Queue       queue = null;
    private JMSProducer producer    = null;
    private String      queueName   = null;

    public JmsProducer(String host, int port, String channel, String queueManagerName, String queueName)
    {
        super(host, port, channel, queueManagerName);
        this.queueName = queueName;
    }

    public void send(String[] messages)
    {
        try (JMSContext context = cf.createContext();){
            queue = context.createQueue(queueName);
//            context.start();
            producer = context.createProducer();

            System.out.println("Getting JMS messages\n");
            TextMessage message = null;
            for (int i = 0; i < messages.length; i++) {
                message = context.createTextMessage(messages[i]);
                producer.send(queue, message);
                System.out.println(String.format("Message #%d was sent", (i + 1)));
            }
        } catch (Exception e) {
            recordFailure(e);
        }
    }
}