package com.ibm.mq.jms.customthread;

import com.ibm.mq.jms.json.JSONExecuter;
import com.ibm.mq.jms.launcher.Runner;
import com.ibm.mq.jms.logics.MyJmsProducer;
import com.ibm.mq.jms.objects.ConnectionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerThread implements Runnable
{
    private CountDownLatch countDownLatch;
    private ReentrantLock locker = new ReentrantLock();

    public ProducerThread(CountDownLatch countDownLatch)
    {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
    {
        locker.lock();
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < Runner.getAmountMessageToSend(); i++) {
            messages.add("Hello " + (i + 1));
        }

        ConnectionInfo conn = new JSONExecuter().read("producer.json");
        MyJmsProducer producer = new MyJmsProducer(conn.getHost(), conn.getPort(), conn.getChannel(),
                                                   conn.getQueueManagerName(), conn.getQueueName());
        producer.send(messages);
        showInfo(messages.size());
        countDownLatch.countDown();
        locker.unlock();
    }

    private void showInfo(int messagesSize)
    {
        Thread thread = new Thread(this);
        System.out.println("Sent " + messagesSize + " messages by " + thread.getId() + " Thread");
    }
}
