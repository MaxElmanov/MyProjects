package com.ibm.mq.jms.logics;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JmsConsumer extends JmsBase {

    private String queueName = null;
    private Queue queue = null;
    private JMSConsumer consumer = null;

    public JmsConsumer(String host, int port, String channel, String queueManagerName, String queueName) {
        super(host, port, channel, queueManagerName);
        this.queueName = queueName;
    }

    public List<String> get() {
        List<String> resultSms = new ArrayList<>();

        try (JMSContext context = cf.createContext();) {
            queue = context.createQueue(queueName);
            consumer = context.createConsumer(queue);

            System.out.println("Getting JMS messages\n");
            TextMessage message = (TextMessage) consumer.receiveNoWait();

            if (Objects.isNull(message)) {
                System.out.println("There is no any messages");
            }
            while (!Objects.isNull(message)) {
                try {
                    resultSms.add(message.getText());
                    message = (TextMessage) consumer.receiveNoWait();
                } catch (JMSException e) {
                    recordFailure(e);
                }
            }
        }

        return resultSms;
    }
}
