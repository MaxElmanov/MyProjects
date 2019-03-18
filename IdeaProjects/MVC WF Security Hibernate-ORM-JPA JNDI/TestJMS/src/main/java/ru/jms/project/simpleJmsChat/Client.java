package ru.jms.project.simpleJmsChat;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

public class Client {

    private String consumerName;
    private String clientID;

    public Client(String consumerName, String clientID) {
        this.consumerName = consumerName;
        this.clientID = clientID;
    }

    public void performWork(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://DESKTOP-5FFID9T:61616");

        try {
            Connection connection = connectionFactory.createConnection();
            connection.setClientID(clientID);
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("chat.example");

            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.setPriority(Message.DEFAULT_PRIORITY);
            producer.setTimeToLive(Message.DEFAULT_TIME_TO_LIVE);

            MessageConsumer consumer = session.createDurableSubscriber(topic, consumerName, "", false);

            MyOwnMessageListener myOwnMessageListener = new MyOwnMessageListener();
            consumer.setMessageListener(myOwnMessageListener);

            connection.start();

            while(true){
                String tempMsg = new Scanner(System.in).nextLine();
                producer.send(session.createTextMessage(consumerName + " : " + tempMsg));

                if(tempMsg.equalsIgnoreCase("END")) {
                    break;
                }
            }

            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
