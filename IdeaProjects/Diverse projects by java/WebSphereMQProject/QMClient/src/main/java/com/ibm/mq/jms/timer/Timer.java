package com.ibm.mq.jms.timer;

import java.util.concurrent.TimeUnit;

public class Timer
{
    private static long timer;
    private static long sum;
    private static long previousCount;
    private static long countMessages;
    private static final byte SECONDS = 1;

    public synchronized void start()
    {
        timer = System.nanoTime();
    }

    public synchronized long stop()
    {
        long dif = System.nanoTime() - timer;
        System.out.println("\nLost time: " + TimeUnit.NANOSECONDS.toMillis(dif) + " ms");
        clearTimer();
        return dif;
    }

    public synchronized long stop(long count)
    {
        long difNano = System.nanoTime() - timer;
        sum += difNano;
        long difSecond = TimeUnit.NANOSECONDS.toSeconds(sum);

        if (difSecond >= SECONDS) {
            countMessages = count - previousCount;
            previousCount = count;
            System.out.printf("%d messages were sent\\got for %d sec\n\n", countMessages, difSecond);
            clearTimer();
            clearSum();
        }

        return difNano;
    }

    private void clearTimer()
    {
        timer = System.nanoTime();
        sum = 0;
    }

    private static void clearSum()
    {
        sum = 0;
    }
}
