package ru.jms.project.threadExampleJMS.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.jms.project.simpleExampleJMS.consumer.HelloWorldConsumer;

import javax.jms.*;

public class HelloWorldConsumerThread implements Runnable  {

    private static final Logger log = LogManager.getLogger(HelloWorldConsumer.class.getName());

    public void run() {

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://DESKTOP-5FFID9T:61616");

            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("HELLO.WORLD.THREAD");

            MessageConsumer consumer = session.createConsumer(destination);

            TextMessage message = (TextMessage) consumer.receive();

            do{
                log.info("CONSUMER " + Thread.currentThread().getName() + " GOT : " + message.getText());

                message = (TextMessage) consumer.receive();

            }while(!(message.equals("END")));


            consumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
