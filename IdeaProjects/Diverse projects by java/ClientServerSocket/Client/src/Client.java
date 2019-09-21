import java.io.*;
import java.net.InetAddress;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String fileName = null;

        try (Phone phone = new Phone("5.255.255.70", 5555)) {
            System.out.println("client connected");

            System.out.print("Enter file name: ");
            fileName = sc.nextLine();

            //form a bufferReader
            BufferedReader br = createBufferReader(fileName);

            //form a request to server
            String fullRequest = getFullRequest(br);

            //send a message to server
            phone.writeLine(fullRequest);

            System.out.println("request: " + Phone.CRLF + fullRequest);

            //get a response from server
            String response = getResponse(phone);

            System.out.println("response: " + Phone.CRLF + response);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getResponse(Phone phone)
    {
        StringBuilder sb = new StringBuilder();
        String response = phone.readLine();

        while (!response.equalsIgnoreCase(Phone.LAST_LINE)) {
            sb.append(response);
            sb.append(System.lineSeparator());
            response = phone.readLine();
        }

        return sb.toString();
    }

    private static String getFullRequest(BufferedReader br) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        String request = br.readLine();

        while (request != null) {
            sb.append(request);
            sb.append(System.lineSeparator());
            request = br.readLine();
        }
        sb.append(Phone.LAST_LINE);

        return sb.toString();
    }

    private static BufferedReader createBufferReader(String fileName)
    {
        InputStream is = Client.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            try {
                throw new Exception();
            }
            catch (Exception e) {
                System.out.println("Such a file is not existing");
                System.exit(-1);
            }
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            return br;
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
