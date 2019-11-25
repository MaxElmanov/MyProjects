package ru.maxelmanov.detagramsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.Scanner;

public class Client
{
    private DatagramSocket socket;
    private Integer port = 8888;
    private InetAddress address;
    private List<String> requestMessages;
    private List<DatagramPacket> requestsPackets;

    private void init()
    {
        try {
            socket = new DatagramSocket();
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void launch()
    {
        try {
            //getting message from user
            byte[] buffer = getAnswer("Enter a name: ");

            //settings for DatagramPacket
            address = InetAddress.getLocalHost(); // identical to "InetAddress.getByAddress("localhost");"
            DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length, address, port);

            socket.send(requestPacket);
            //----------------------------------------------------------------------------------------------------------

            while(true) {
                byte[] bufferRequest = new byte[1024];
                DatagramPacket request = new DatagramPacket(bufferRequest, bufferRequest.length);
                socket.receive(request);


                byte[] answer = getAnswer(new String(request.getData()) + ": ");
                DatagramPacket reply = new DatagramPacket(answer, answer.length, request.getAddress(), request.getPort());
                socket.send(reply);
            }
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] getAnswer(String expression) throws IOException
    {
        System.out.print(expression);
        String answer = new Scanner(System.in).nextLine();
        return answer.getBytes();
    }

    public static void main(String[] args)
    {
        Client client = new Client();
        client.init();
        client.launch();
    }
}
