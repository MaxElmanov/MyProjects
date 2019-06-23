package ru.jms.project.simpleExampleJMS.producer;

import ru.jms.project.simpleExampleJMS.consumer.HelloWorldConsumer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

public class HelloWorldProducer {

    private static final Logger log = LogManager.getLogger(HelloWorldConsumer.class.getName());

    public void go(){

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://DESKTOP-5FFID9T:61616");

        Connection connection = null;
        Session session = null;

        try {
            connection = connectionFactory.createConnection();

            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            Destination destination = session.createQueue("HELLO.WORLD.TEST");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            producer.send(session.createTextMessage("Greeting by Producer from " + HelloWorldProducer.class.getName()));
            producer.send(session.createTextMessage("Greeting by Producer from " + HelloWorldProducer.class.getName()));
            producer.send(session.createTextMessage("END"));

            log.info("Producer sent his massage");

            producer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
