package ru.jms.project.threadExampleJMS;

import ru.jms.project.threadExampleJMS.consumer.HelloWorldConsumerThread;
import ru.jms.project.threadExampleJMS.producer.HelloWorldProducerThread;

public class Run_2 {

    public static void main(String[] args) throws InterruptedException {

        thread(new HelloWorldProducerThread(), false);//P - 1
        thread(new HelloWorldProducerThread(), false);//P - 2
        thread(new HelloWorldProducerThread(), false);//P - 3
        thread(new HelloWorldProducerThread(), false);//P - 4
        Thread.sleep(1000);

        thread(new HelloWorldConsumerThread(), false);//C - 2
        thread(new HelloWorldProducerThread(), false);//P - 5
        thread(new HelloWorldConsumerThread(), false);//C - 3
        thread(new HelloWorldProducerThread(), false);//P - 6
        thread(new HelloWorldConsumerThread(), false);//C - 4
        thread(new HelloWorldConsumerThread(), false);//C - 5
        Thread.sleep(1000);

    }

    private static void thread(Runnable runner, boolean daemon){
        Thread thread = new Thread(runner);
        thread.setDaemon(daemon);
        thread.start();
    }

}
