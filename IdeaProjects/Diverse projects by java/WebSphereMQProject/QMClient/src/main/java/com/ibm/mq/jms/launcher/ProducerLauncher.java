package com.ibm.mq.jms.launcher;

import com.ibm.mq.jms.json.JSONExecuter;
import com.ibm.mq.jms.json.objects.ConnectionInfo;
import com.ibm.mq.jms.logics.JmsProducer;

public class ProducerLauncher {

    public static void main(String[] args)
    {
        // Создание текстовых сообщений
        String[] messages = new String[20];
        for(int i = 0; i < 20; i++){
            messages[i] = "Hello " + (i+1);
        }

        JSONExecuter json = new JSONExecuter();
        ConnectionInfo conn = json.read("producer.json");
        JmsProducer producer = new JmsProducer(conn.getHost(), conn.getPort(), conn.getChannel(), conn.getQueueManagerName(), conn.getQueueName());
        producer.send(messages);
    }
}
