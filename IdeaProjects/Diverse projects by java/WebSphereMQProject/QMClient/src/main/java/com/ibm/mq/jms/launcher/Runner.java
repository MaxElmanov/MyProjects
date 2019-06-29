package com.ibm.mq.jms.launcher;

import com.ibm.mq.jms.customthread.ConsumerThread;
import com.ibm.mq.jms.customthread.ProducerThread;
import com.ibm.mq.jms.timer.Timer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Runner
{
    private static final byte CORE_POOL_SIZE = 2;
    private static final byte MAXIMUM_POOL_SIZE = 4;
    private static final byte KEEP_ALIVE_TIME = 10;
    private static final byte AMOUNT_THREADS = 7;
    private static final byte AMOUNT_MESSAGE_TO_SEND = 10;

    public static void main(String[] args)
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1),
                new MyReject());

        if (args.length == 0 || args.length > 1)
        {
            System.out.println("Error: only one parameter must be pointed \nc-consumer \np-producer");
        } else if (args[0].equalsIgnoreCase("c"))
        {
            Timer.start();
            for (int i = 0; i < AMOUNT_THREADS; i++)
            {
                executor.submit(new ConsumerThread());
            }
            Timer.stop();
        } else if (args[0].equalsIgnoreCase("p"))
        {
            Timer.start();
            for (int i = 0; i < AMOUNT_THREADS; i++)
            {
                executor.submit(new ProducerThread());
            }
            Timer.stop();
        }

        executor.shutdown();

//        if (executor.isShutdown())
//        {
//            end(executor);
//        }
    }

    private static void end(ThreadPoolExecutor executor)
    {
        System.out.println("ActiveCount = " + executor.getActiveCount() + "\n" +
                "CompletedTaskCount = " + executor.getCompletedTaskCount() + "\n" +
                "CorePoolSize = " + executor.getCorePoolSize() + "\n" +
                "KeepAliveTime = " + executor.getKeepAliveTime(TimeUnit.MILLISECONDS) + "\n" +
                "TaskCount = " + executor.getTaskCount() + "\n" +
                "LargestPoolSiz e= " + executor.getLargestPoolSize() + "\n" +
                "MaximumPoolSize = " + executor.getMaximumPoolSize() + "\n" +
                "THE END");

    }

    public static byte getAmountMessageToSend()
    {
        return AMOUNT_MESSAGE_TO_SEND;
    }
}

class MyReject implements RejectedExecutionHandler
{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
    {
        Thread thread = new Thread(r);
        System.out.println("Thread id:" + thread.getId() + " was rejected");
    }
}
