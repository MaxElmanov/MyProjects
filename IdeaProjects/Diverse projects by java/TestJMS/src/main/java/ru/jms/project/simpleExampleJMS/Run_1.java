package ru.jms.project.simpleExampleJMS;

import ru.jms.project.simpleExampleJMS.consumer.HelloWorldConsumer;
import ru.jms.project.simpleExampleJMS.producer.HelloWorldProducer;

public class Run_1 {

    public static void main(String[] args) {

//        (1)producer -> queue -> consumer(1)

        new HelloWorldProducer().go();
        new HelloWorldConsumer().go();

    }

}
