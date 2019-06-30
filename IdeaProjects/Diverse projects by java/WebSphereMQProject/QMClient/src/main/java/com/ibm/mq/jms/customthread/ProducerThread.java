package com.ibm.mq.jms.customthread;

import com.ibm.mq.jms.json.JSONExecuter;
import com.ibm.mq.jms.launcher.Runner;
import com.ibm.mq.jms.logics.JmsProducer;
import com.ibm.mq.jms.objects.ConnectionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ProducerThread implements Runnable
{
    CountDownLatch countDownLatch;

    public ProducerThread(CountDownLatch countDownLatch)
    {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
    {
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < Runner.getAmountMessageToSend(); i++)
        {
            messages.add("Hello " + (i + 1));
        }

        ConnectionInfo conn = new JSONExecuter().read("producer.json");
        JmsProducer producer = new JmsProducer(conn.getHost(), conn.getPort(), conn.getChannel(),
                conn.getQueueManagerName(), conn.getQueueName());
        producer.send(messages);
        showInfo(messages);
        countDownLatch.countDown();
    }

    private void showInfo(List<String> messages)
    {
        Thread thread = new Thread(this);
        System.out.println("Sent " + messages.size() + " messages by " + thread.getId() + " Thread");
    }
}
