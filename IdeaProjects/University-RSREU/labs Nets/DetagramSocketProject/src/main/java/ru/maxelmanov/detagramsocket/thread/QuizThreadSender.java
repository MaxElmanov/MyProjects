package ru.maxelmanov.detagramsocket.thread;

import ru.maxelmanov.detagramsocket.objs.Question;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class QuizThreadSender extends Thread
{
    private DatagramSocket socket;
    private List<DatagramPacket> requestsPackets;
    private List<String> requestMessages;
    private volatile boolean stopMe = false;
    private DatagramPacket request;
    private DatagramPacket reply;
    private List<Question> quiz;
    private ReentrantLock lock;

    public QuizThreadSender(DatagramPacket request, List<Question> quiz, DatagramSocket socket)
    {
        this.request = request;
        this.quiz = quiz;
        this.socket = socket;
        lock = new ReentrantLock();
    }

    @Override
    public void run()
    {
        lock.lock(); //synchronized block

        System.out.println("Quiz was started");

        int indexQuestion = 0;

        try {
            askQuestion(indexQuestion, request);
            Thread.sleep(9000);

            indexQuestion++;
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        lock.unlock(); //synchronized block
    }

    private void askQuestion(int index, DatagramPacket request) throws IOException
    {
        StringBuilder replyMessage = new StringBuilder();

        //fill up the message of question
        Question question = quiz.get(index);

        replyMessage.append(question.getTestQuestion()).append("\n");

        List<String> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            replyMessage.append((i + 1) + " - " + answers.get(i)).append("\n");
        }

        reply = new DatagramPacket(replyMessage.toString().getBytes(), replyMessage.toString().getBytes().length, request.getAddress(), request.getPort());
        socket.send(reply);
    }

    public void stopMe()
    {
        stopMe = true;
    }
}
