package com.ibm.mq.jms.timer;

public class Timer
{
    private static long timer;

    public static void start()
    {
        timer = System.nanoTime();
    }

    public static long stop()
    {
        long result = System.nanoTime() - timer;
        System.out.println("Lost time: " + result);
        return result;
    }
}
