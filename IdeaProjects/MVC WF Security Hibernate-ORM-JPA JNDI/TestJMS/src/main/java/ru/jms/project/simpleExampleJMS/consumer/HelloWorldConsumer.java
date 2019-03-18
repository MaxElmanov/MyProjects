package ru.jms.project.simpleExampleJMS.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

public class HelloWorldConsumer {

    private static final Logger log = LogManager.getLogger(HelloWorldConsumer.class.getName());

    public void go() {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://DESKTOP-5FFID9T:61616");

        Connection connection = null;
        Session session = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            Destination destination = session.createQueue("HELLO.WORLD.TEST");

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive();

            if(!(message instanceof TextMessage)){
                throw new Exception("message should be a TextMassage instance");
            }

            TextMessage textMessage = (TextMessage) message;

            do {
                textMessage = (TextMessage) consumer.receive();

                //if connection.createSession(false, Session.AUTO_ACKNOWLEDGE); then this invocation is not necessary
                textMessage.acknowledge();

                log.info("CONSUMER : " + textMessage.getText());
            }while(!textMessage.equals("END"));

            consumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
