package ru.maxelmanov.detagramsocket.objs;

import java.net.InetAddress;

public class Participant
{
    private String name;
    private int place;
    private InetAddress address;
    private int port;

    public Participant(String name, InetAddress address, int port)
    {
        this.name = name;
        this.address = address;
        this.port = port;
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
}
