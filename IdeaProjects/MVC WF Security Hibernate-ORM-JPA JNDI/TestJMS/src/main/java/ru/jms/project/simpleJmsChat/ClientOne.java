package ru.jms.project.simpleJmsChat;

public class ClientOne {
    public static void main(String[] args) {
        System.out.println("You (Client 1) can start chatting");
        Client client1 = new Client(ClientIDs.ClientOne.toString(), "chat client 1");
        client1.performWork();
    }
}
