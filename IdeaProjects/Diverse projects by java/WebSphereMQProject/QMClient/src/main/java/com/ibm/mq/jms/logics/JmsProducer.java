package com.ibm.mq.jms.logics;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import java.util.List;

public class JmsProducer extends JmsBase
{

    private Queue queue = null;
    private JMSProducer producer = null;
    private String queueName = null;

    public JmsProducer(String host, int port, String channel, String queueManagerName, String queueName)
    {
        super(host, port, channel, queueManagerName);
        this.queueName = queueName;
    }

    public void send(List<String> messages)
    {
        try (JMSContext context = cf.createContext())
        {
            queue = context.createQueue(queueName);
            producer = context.createProducer();

            TextMessage message = null;
            for (int i = 0; i < messages.size(); i++)
            {
                message = context.createTextMessage(messages.get(i));
                producer.send(queue, message);
                System.out.println(String.format("Message #%d was sent", (i + 1)));
            }
        } catch (Exception e)
        {
            recordFailure(e);
        }
    }
}