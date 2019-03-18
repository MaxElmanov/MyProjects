package ru.jms.project.asyncExampleJMS;

public class Run {
    public static void main(String[] args) {
        new AsyncClientProducerQueue().main(null);
        new AsyncClientReceiverQueue().main(null);
    }
}
