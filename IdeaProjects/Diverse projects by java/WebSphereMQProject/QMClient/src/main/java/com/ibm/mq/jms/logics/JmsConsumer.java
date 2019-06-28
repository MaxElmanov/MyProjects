package com.ibm.mq.jms.logics;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JmsConsumer extends JmsBase {

    private String          queueName   = null;
    private Session         session     = null;
    private Destination     destination = null;
    private MessageConsumer consumer    = null;

    public JmsConsumer(String host, int port, String channel, String queueManagerName, String queueName)
    {
        super(host, port, channel, queueManagerName);
        this.queueName = queueName;
    }

    public List<String> get()
    {
        List<String> resultSms = new ArrayList<>();

        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(queueName);
            connection.start();

            System.out.println("Getting JMS messages\n");
            consumer = session.createConsumer(destination);

            Message message = consumer.receiveNoWait();
            while(!Objects.isNull(message)){
                resultSms.add(message.toString());
                message = consumer.receiveNoWait();
            }

        } catch (JMSException e) {
            recordFailure(e);
        }
        finally {
            if(session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    recordFailure(e);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    recordFailure(e);
                }
            }
        }

        return resultSms;
    }
}
