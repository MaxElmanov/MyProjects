package com.ibm.mq.jms.launcher;

import com.ibm.mq.jms.logics.JmsConsumer;

import java.util.List;

public class ConsumerLauncher {

    private static String   host              = "localhost"         ;
    private static int      port              = 1415                ;
    private static String   channel           = "SYSTEM.DEF.SVRCONN";
    private static String   queueManagerName  = "IBMESBQM2"         ;
    private static String   queueName         = "IBM.ESB.IN"        ;

    public static void main(String[] args) {
        JmsConsumer consumer = new JmsConsumer(host, port, channel, queueManagerName, queueName);
        List<String> resultSms = consumer.get();
        for(String str : resultSms) {
            System.out.println(str + "\n--------------------");
        }
    }
}
