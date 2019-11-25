package ru.maxelmanov.detagramsocket.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class QuizThreadObtainer extends Thread
{
    private DatagramSocket socket;
    private List<DatagramPacket> requestsPackets;
    private List<String> requestMessages;
    private volatile boolean stopMe = false;
    private ReentrantLock lock;

    public QuizThreadObtainer(DatagramSocket socket)
    {
        this.socket = socket;
        lock = new ReentrantLock();
    }

    @Override
    public void run()
    {
        lock.lock(); //synchronized block

        try {
            getAnswer();
            Thread.sleep(9000);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock(); //synchronized block
    }

    private void getAnswer() throws IOException
    {
        byte[] buffer = new byte[1024];
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        socket.receive(request);
    }

    public void stopMe()
    {
        stopMe = true;
    }
}
