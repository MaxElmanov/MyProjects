package ru.jms.project.threadExampleJMS.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

public class HelloWorldProducerThread implements Runnable  {

    private static final Logger log = LogManager.getLogger(HelloWorldProducerThread.class.getName());

    public void run() {

        try{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://DESKTOP-5FFID9T:61616");

            Connection connection = connectionFactory.createConnection();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("HELLO.WORLD.THREAD");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            String text = "Hello, I'm producer " + Thread.currentThread().getName();

            TextMessage message = session.createTextMessage(text);

            producer.send(message);

            log.info("Producer " + Thread.currentThread().getName() + " sent his massage");

            producer.close();
            session.close();
            connection.close();

        }
        catch (Exception e){
            e.fillInStackTrace();
        }

    }

}
