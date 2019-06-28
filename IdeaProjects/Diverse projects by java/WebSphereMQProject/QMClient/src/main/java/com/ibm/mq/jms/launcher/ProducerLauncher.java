package com.ibm.mq.jms.launcher;

import com.ibm.mq.jms.logics.JmsProducer;

public class ProducerLauncher {

    private static String   host              = "localhost"         ;
    private static int      port              = 1416                ;
    private static String   channel           = "SYSTEM.DEF.SVRCONN";
    private static String   queueManagerName  = "IBMESBQM3"         ;
    private static String   queueName         = "INPUT"             ;
//    private String   queueName         = "IBM.ESB.IN"        ;

    public static void main(String[] args)
    {
        // Создание текстовых сообщений
        String[] messages = new String[9];
        messages[0] = "Good day";
        messages[1] = "Hello, world! 0";
        messages[2] = "Hello, world! 1";
        messages[3] = "Hello, world! 2";
        messages[4] = "Hello, world! 3";
        messages[5] = "Hello, world! 4";
        messages[6] = "Hello, world! 5";
        messages[7] = "Hello, world! 6";
        messages[8] = "Hello, world! 7";

        JmsProducer producer = new JmsProducer(host, port, channel, queueManagerName, queueName);
        producer.send(messages);
    }
}
