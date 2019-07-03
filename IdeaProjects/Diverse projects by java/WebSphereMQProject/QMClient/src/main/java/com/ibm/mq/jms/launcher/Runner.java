package com.ibm.mq.jms.launcher;

import com.ibm.mq.jms.customthread.ConsumerThread;
import com.ibm.mq.jms.customthread.ProducerThread;
import com.ibm.mq.jms.timer.Timer;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Runner
{
    private static final byte CORE_POOL_SIZE = 3;
    private static final byte MAXIMUM_POOL_SIZE = 3;
    private static final byte KEEP_ALIVE_TIME = 10;
    private static final byte AMOUNT_THREADS = 7;
    private static final byte AMOUNT_PLACES_IN_QUEUE = 10;
    private static final byte AMOUNT_MESSAGE_TO_SEND = 30;

    public static void main(String[] args) throws InterruptedException
    {
        Timer timer = new Timer();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                                                             MAXIMUM_POOL_SIZE,
                                                             KEEP_ALIVE_TIME,
                                                             TimeUnit.MILLISECONDS,
                                                             new LinkedBlockingQueue<>(AMOUNT_PLACES_IN_QUEUE),
                                                             new MyReject());

        CountDownLatch countDownLatch = new CountDownLatch(AMOUNT_THREADS);

        if (args.length == 0 || args.length > 1) {
            System.out.println("Error: only one parameter must be pointed \nc-consumer \np-producer");
        }
        else if (args[0].equalsIgnoreCase("c")) {
            System.out.println("Getting");
            timer.start();
            for (int i = 0; i < AMOUNT_THREADS; i++) {
                executor.submit(new ConsumerThread(countDownLatch));
            }
        }
        else if (args[0].equalsIgnoreCase("p")) {
            System.out.println("Sending");
            timer.start();
            for (int i = 0; i < AMOUNT_THREADS; i++) {
                executor.submit(new ProducerThread(countDownLatch));
            }
        }

        executor.shutdown();

        countDownLatch.await();
        end(executor);

        timer.stop();
    }

    private static void end(ThreadPoolExecutor executor)
    {
        System.out.println("\nActiveCount = " + executor.getActiveCount() + "\n" +
                                   "CompletedTaskCount = " + executor.getCompletedTaskCount() + "\n" +
                                   "CorePoolSize = " + executor.getCorePoolSize() + "\n" +
                                   "KeepAliveTime = " + executor.getKeepAliveTime(TimeUnit.MILLISECONDS) + "\n" +
                                   "TaskCount = " + executor.getTaskCount() + "\n" +
                                   "LargestPoolSize = " + executor.getLargestPoolSize() + "\n" +
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
