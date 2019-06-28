package com.ibm.mq.jms.launcher;

import com.ibm.mq.jms.json.JSONExecuter;
import com.ibm.mq.jms.json.objects.ConnectionInfo;
import com.ibm.mq.jms.logics.JmsConsumer;
import com.ibm.mq.jms.logics.JmsProducer;

public class ProducerLauncher {

//    private static String   host              = "localhost"         ;
//    private static int      port              = 1416                ;
//    private static String   channel           = "SYSTEM.DEF.SVRCONN";
//    private static String   queueManagerName  = "IBMESBQM3"         ;
//    private static String   queueName         = "INPUT"             ;
//    private String   queueName         = "IBM.ESB.IN"        ;

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
