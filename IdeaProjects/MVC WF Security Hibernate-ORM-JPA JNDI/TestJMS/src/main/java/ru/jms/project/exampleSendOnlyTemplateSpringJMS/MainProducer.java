package ru.jms.project.exampleSendOnlyTemplateSpringJMS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MainProducer {

    private static final Logger log = LogManager.getLogger(MainProducer.class.getName());

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context_sendonly.xml");
        JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");

        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("Hello from class named MainProducer");
                return textMessage;
            }
        };

        template.send(messageCreator);

        log.info("template sent a message");

        System.exit(0);

    }
}
