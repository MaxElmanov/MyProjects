package com.ibm.mq.jms.customthread;

import com.ibm.mq.jms.json.JSONExecuter;
import com.ibm.mq.jms.logics.JmsConsumer;
import com.ibm.mq.jms.objects.ConnectionInfo;

import java.util.List;

public class ConsumerThread implements Runnable
{
    @Override
    public void run()
    {
        ConnectionInfo conn = new JSONExecuter().read("consumer.json");
        JmsConsumer consumer = new JmsConsumer(conn.getHost(), conn.getPort(), conn.getChannel(),
                conn.getQueueManagerName(), conn.getQueueName());
        List<String> resultSms = consumer.get();
        showInfo(resultSms);
        for (String str : resultSms)
        {
            System.out.println(str + "\n--------------------");
        }

    }

    private void showInfo(List<String> resultSms)
    {
        Thread thread = new Thread(this);
        System.out.println("Got " + resultSms.size() + " messages by " + thread.getId() + " Thread");
    }
}
