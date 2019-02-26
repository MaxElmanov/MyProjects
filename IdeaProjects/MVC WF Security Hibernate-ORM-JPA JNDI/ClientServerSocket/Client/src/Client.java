import elmanov.Phone;

import java.io.IOException;

public class Client {

    public static void main(String[] args) {

        try(Phone phone = new Phone("127.0.0.1", 8000))
        {

            System.out.println("client connected");

            String request = "Ryazan";
            System.out.println("request: " + request);

            phone.writeLine(request);

            String response = phone.readLine();
            System.out.println("response: " + response);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
