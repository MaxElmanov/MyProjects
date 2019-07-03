package com.ibm.mq.jms.logics;

import com.ibm.mq.jms.timer.Timer;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

public class MyJmsConsumer extends JmsBase
{
    private static final long TIME_WAITING = 2000;
    private String queueName = null;
    private Queue queue = null;
    private JMSConsumer consumer = null;
    Timer timer = new Timer();

    public MyJmsConsumer(String host, int port, String channel, String queueManagerName, String queueName)
    {
        super(host, port, channel, queueManagerName);
        this.queueName = queueName;
    }

    public List<String> get()
    {
        List<String> resultSms = new ArrayList<>();

        try (JMSContext context = cf.createContext();) {
            queue = context.createQueue(queueName);
            consumer = context.createConsumer(queue);

            String message;
            timer.start();
            for (int numberMessage = 0; true; numberMessage++) {
                try {
                    message = consumer.receiveBody(String.class, TIME_WAITING);
                    timer.stop(numberMessage);
                    resultSms.add(message);
                }
                catch (Exception e) {
                    recordFailure(e);
                }
            }
        }
        finally {
            if (consumer != null) {
                consumer.close();
            }
        }

//        return resultSms;
    }
}
