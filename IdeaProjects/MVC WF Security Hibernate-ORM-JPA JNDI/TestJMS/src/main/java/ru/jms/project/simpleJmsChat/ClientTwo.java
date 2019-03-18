package ru.jms.project.simpleJmsChat;

public class ClientTwo {
    public static void main(String[] args) {
        System.out.println("You (Client 2) can start chatting");
        Client client2 = new Client(ClientIDs.ClientTwo.toString(), "chat client 2");
        client2.performWork();
    }

}
