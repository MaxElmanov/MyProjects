package ru.jms.project.asyncExampleJMS;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AsyncClientProducerQueue {

    public static void main(String[] args) {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://DESKTOP-5FFID9T:61616");

        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("async.example");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            for (int i = 0; i < 10; i++) {
                TextMessage message = session.createTextMessage("message #" + i + " hey my friend");
                System.out.println("producer sent message : " + message.getText());
                producer.send(message);
            }

            //for end
            TextMessage message = session.createTextMessage("END");
            System.out.println("producer sent message : " + message.getText());
            producer.send(message);

            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
