package ru.jms.project.asyncExampleJMS;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;

public class AsyncClientReceiverQueue {

    private CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        AsyncClientReceiverQueue asyncClientReceiverQueue = new AsyncClientReceiverQueue();
        asyncClientReceiverQueue.receiveMessage();
    }

    private void receiveMessage(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://DESKTOP-5FFID9T:61616");
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("async.example");
            MessageConsumer consumer = session.createConsumer(destination);

            ClientListener clientListener = new ClientListener("Customer");
            clientListener.setAsyncClientReceiverQueue(this);

            consumer.setMessageListener(clientListener);

            connection.start();
            latch.await();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
                try {
                    if(session != null) {
                        session.close();
                    }
                    if(connection != null) {
                        connection.close();
                    }
                    } catch (JMSException e) {
                    e.printStackTrace();
                }
        }
    }

    public void latchCountDown(){
        latch.countDown();
    }

}
