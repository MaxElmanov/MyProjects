package ru.maxelmanov.detagramsocket.timer;

public class TimerClass extends Thread
{
    private int counter;

    public TimerClass(int counter)
    {
        this.counter = counter;
    }

    public void run()
    {
        try {
            while (counter >= 1) {
                counter--;
                System.out.println(counter);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
