import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server
{
    public static void main(String[] args) throws UnknownHostException
    {
        List<String> hostNames = new ArrayList<String>(){
            {add("localhost");}
            {add("yandex.ru");}
            {add("google.com");}
            {add(InetAddress.getLocalHost().getHostName());} //DESKTOP-5FFID9T
        };

        try (ServerSocket serverSocket = new ServerSocket(5555)) {

            while (true)
                try (Phone phone = new Phone(serverSocket, hostNames)) {

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
                    StringBuilder response = new StringBuilder().append(getRequestStatus(sb.toString()) + Phone.CRLF);

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

    private static String getRequestStatus(String request)
    {
        if (request.toUpperCase().contains("CREATE")) {
            return "Table was successfully created. ";
        }
        else if (request.toUpperCase().contains("SELECT")) {
            return "Select query was successfully executed. ";
        }
        else if (request.toUpperCase().contains("DELETE")) {
            return "Delete query was successfully executed. ";
        }
        else {
            return "Request was successfully performed. ";
        }
    }
}
