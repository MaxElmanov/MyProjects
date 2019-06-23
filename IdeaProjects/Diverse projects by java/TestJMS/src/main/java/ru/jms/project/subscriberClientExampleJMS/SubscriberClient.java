package ru.jms.project.subscriberClientExampleJMS;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SubscriberClient {
    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://DESKTOP-5FFID9T:61616");
        try {
            Connection connection = connectionFactory.createConnection();
            connection.setClientID("DurabilityTest");
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("subscribeTopic");

            //publisher
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.setPriority(Message.DEFAULT_PRIORITY);
            producer.setTimeToLive(Message.DEFAULT_TIME_TO_LIVE);
            //work
            TextMessage message = session.createTextMessage("task payload");
            producer.send(message);
            System.out.println("producer sent message - '" + message.getText() + "'");

            //consumer 1
            MessageConsumer consumer1 = session.createDurableSubscriber(topic, "consumer1", "", false);

            //consumer 2
            MessageConsumer consumer2 = session.createDurableSubscriber(topic, "consumer2", "", false);

            connection.start();

            //getting message by consumer1
            TextMessage msgConsumer1 = (TextMessage) consumer1.receive();
            System.out.println("consumer1 received message : " + msgConsumer1.getText());

            //getting message by consumer2
            TextMessage msgConsumer2 = (TextMessage) consumer2.receive();
            System.out.println("consumer2 received message : " + msgConsumer2.getText());

            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
