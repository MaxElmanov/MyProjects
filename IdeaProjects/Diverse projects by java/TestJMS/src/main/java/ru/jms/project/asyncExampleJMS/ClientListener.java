package ru.jms.project.asyncExampleJMS;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ClientListener implements MessageListener {

    private String consumerName;
    private AsyncClientReceiverQueue asyncClientReceiverQueue;

    public ClientListener(String consumerName) {
        this.consumerName = consumerName;
    }

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println(consumerName + " received : " + textMessage.getText());

            if(textMessage.getText().equals("END")) {
                asyncClientReceiverQueue.latchCountDown();
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void setAsyncClientReceiverQueue(AsyncClientReceiverQueue asyncClientReceiverQueue) {
        this.asyncClientReceiverQueue = asyncClientReceiverQueue;
    }
}
