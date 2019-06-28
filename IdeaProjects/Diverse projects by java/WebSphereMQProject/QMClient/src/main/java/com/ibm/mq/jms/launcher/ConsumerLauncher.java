package com.ibm.mq.jms.launcher;

import com.ibm.mq.jms.json.JSONExecuter;
import com.ibm.mq.jms.json.objects.ConnectionInfo;
import com.ibm.mq.jms.logics.JmsConsumer;

import java.util.List;

public class ConsumerLauncher {

    public static void main(String[] args) {
        JSONExecuter json = new JSONExecuter();
        ConnectionInfo conn = json.read("consumer.json");
        JmsConsumer consumer = new JmsConsumer(conn.getHost(), conn.getPort(), conn.getChannel(), conn.getQueueManagerName(), conn.getQueueName());
        List<String> resultSms = consumer.get();
        for(String str : resultSms) {
            System.out.println(str + "\n--------------------");
        }
    }
}
