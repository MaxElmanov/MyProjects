package ru.maxelmanov.detagramsocket.objs;

import java.net.InetAddress;
import java.util.List;

public class Participant
{
    private int id = 0;
    private String name;
    private int place;
    private int score;
    private InetAddress address;
    private int port;
    private List<String> answers;

    public Participant(String name, InetAddress address, int port)
    {
        id += 1;
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public int getId()
    {
        return id;
    }

    public List<String> getAnswers()
    {
        return answers;
    }

    public void setAnswer(String answer)
    {
        this.answers.add(answer);
    }

    public InetAddress getAddress()
    {
        return address;
    }

    public int getPort()
    {
        return port;
    }

    public String getName()
    {
        return name;
    }

    public int getPlace()
    {
        return place;
    }

    public void setPlace(int place)
    {
        this.place = place;
    }

    public int analize(List<Question> quiz)
    {
        getScore(quiz);
        return score;
    }

    private void getScore(List<Question> quiz)
    {
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equalsIgnoreCase(quiz.get(i).getRightAnswer())) {
                score++;
            }
        }
    }
}
