package com.ibm.mq.jms.customthread;

import com.ibm.mq.jms.json.JSONExecuter;
import com.ibm.mq.jms.logics.MyJmsConsumer;
import com.ibm.mq.jms.objects.ConnectionInfo;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerThread implements Runnable
{
    private CountDownLatch countDownLatch;
    private ReentrantLock locker = new ReentrantLock();

    public ConsumerThread(CountDownLatch countDownLatch)
    {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
    {
        locker.lock();
        ConnectionInfo conn = new JSONExecuter().read("consumer.json");
        MyJmsConsumer consumer = new MyJmsConsumer(conn.getHost(), conn.getPort(), conn.getChannel(),
                                                   conn.getQueueManagerName(), conn.getQueueName());
        List<String> resultSms = consumer.get();
        for (String str : resultSms) {
            System.out.println(str + "\n--------------------");
        }
        showInfo(resultSms);
        countDownLatch.countDown();
        locker.unlock();
    }

    private void showInfo(List<String> resultSms)
    {
        Thread thread = new Thread(this);
        System.out.println("Got " + resultSms.size() + " messages by " + thread.getId() + " Thread");
    }
}
