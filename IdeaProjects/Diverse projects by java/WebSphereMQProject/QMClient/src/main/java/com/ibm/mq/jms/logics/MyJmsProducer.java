package com.ibm.mq.jms.logics;

import com.ibm.mq.jms.timer.Timer;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import java.util.List;

public class MyJmsProducer extends JmsBase
{

    private Queue queue = null;
    private JMSProducer producer = null;
    private String queueName = null;
    Timer timer = new Timer();

    public MyJmsProducer(String host, int port, String channel, String queueManagerName, String queueName)
    {
        super(host, port, channel, queueManagerName);
        this.queueName = queueName;
    }

    public void send(List<String> messages)
    {
        try (JMSContext context = cf.createContext()) {
            queue = context.createQueue(queueName);
            producer = context.createProducer();

            String message = null;
            timer.start();
            for (int smsNumber = 0; smsNumber < messages.size(); smsNumber++) {
                message = messages.get(smsNumber);
                producer.send(queue, message);
                timer.stop(smsNumber);
//                System.out.println(String.format("Message #%d was sent", (smsNumber + 1)));
            }
        }
        catch (Exception e) {
            recordFailure(e);
        }
    }
}