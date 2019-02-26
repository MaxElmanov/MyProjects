import elmanov.Phone;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(8000)){

            while (true)
                try (Phone phone = new Phone(serverSocket)) {

                    String request = phone.readLine();

                    int max = request.length();
                    int min = 0;

                    String response = String.valueOf(Math.floor(Math.random() * (max - min + 1)) + min);
                    phone.writeLine(response);
                    System.out.println("request: " + request);
                }
                catch (IOException | NullPointerException e){
                    throw  new RuntimeException(e);
                }
        }
        catch (IOException e){
            throw  new RuntimeException(e);
        }

    }

}
