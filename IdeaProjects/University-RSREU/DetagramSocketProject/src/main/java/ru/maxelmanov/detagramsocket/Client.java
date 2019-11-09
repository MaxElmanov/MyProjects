package ru.maxelmanov.detagramsocket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        try (DatagramSocket socket = new DatagramSocket()) {

            //getting message from user
            System.out.print("Enter a message: ");
            String message = new Scanner(System.in).nextLine();
            byte[] buffer = message.getBytes();

            //settings for DatagramPacket
            Integer port = 8888;
            InetAddress address = InetAddress.getLocalHost(); // identical to "InetAddress.getByAddress("localhost");"
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);

            socket.send(packet);
            //----------------------------------------------------------------------------------------------------------
            byte[] replyBuffer = new byte[1000];

            DatagramPacket reply = new DatagramPacket(replyBuffer, replyBuffer.length);

            socket.receive(reply);
            String receivedMessage = new String(reply.getData(), "UTF-8");
            System.out.println(receivedMessage);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
