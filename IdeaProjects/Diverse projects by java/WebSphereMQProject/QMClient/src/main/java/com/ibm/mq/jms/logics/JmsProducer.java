package com.ibm.mq.jms.logics;

import javax.jms.*;

public class JmsProducer extends JmsBase {

    private Session         session     = null;
    private Queue           destination = null;
    private MessageProducer producer    = null;
    private String          queueName   = null;

    public JmsProducer(String host, int port, String channel, String queueManagerName, String queueName)
    {
        super(host, port, channel, queueManagerName);
        this.queueName = queueName;
    }

    public void send(String[] messages)
    {
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(queueName);
            connection.start();

            System.out.println("Getting JMS messages\n");
            producer = session.createProducer(destination);
            TextMessage message = null;
            for (int i = 0; i < messages.length; i++) {
                message = session.createTextMessage(messages[i]);
                producer.send(message);
                System.out.println(String.format("Message #%d was sent", (i + 1)));
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
    }
}