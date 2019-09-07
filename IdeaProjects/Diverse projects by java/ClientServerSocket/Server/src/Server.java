import java.io.IOException;
import java.net.ServerSocket;

public class Server
{

    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while (true)
                try (Phone phone = new Phone(serverSocket)) {

                    StringBuilder sb = new StringBuilder();

                    //read a message from a client
                    String str = phone.readLine();

                    while (!str.equalsIgnoreCase(Phone.LAST_LINE)) {
                        sb.append(str);
                        sb.append(System.lineSeparator());
                        str = phone.readLine();
                    }

                    //form a response for a client
                    String[] strs = sb.toString().split(Phone.CRLF);
                    StringBuilder response = new StringBuilder().append("Request was successfully performed. Here is:" + Phone.CRLF);

                    for (int i = 0; i < strs.length; i++) {
                        response.append((i + 1) +
                                        " - " +
                                        strs[i] +
                                        Phone.CRLF);
                    }
                    response.append(Phone.LAST_LINE);

                    //send message to a client
                    phone.writeLine(response.toString());

                    System.out.println("response to client: "
                                               + Phone.CRLF + response.toString());
                }
                catch (IOException | NullPointerException e) {
                    throw new RuntimeException(e);
                }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
