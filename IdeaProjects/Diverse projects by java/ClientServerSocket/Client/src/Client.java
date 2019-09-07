import java.io.*;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
//        try {
//            InetAddress[] iaRemoteAll = InetAddress.getAllByName("yandex.ru");
//            for(int i = 0;i < iaRemoteAll.length; i++)
//            {
//                InetAddress ia = iaRemoteAll[i];
//                System.out.println("Address: " + ia);
//                System.out.println("Host name: " +
//                                           ia.getHostName());
//                byte[] ip = new byte[4];
//                ip = ia.getAddress();
//                System.out.println("IP Address: " +
//                                           (0xff & (int)ip[0]) + "." +
//                                           (0xff & (int)ip[1]) + "." +
//                                           (0xff & (int)ip[2]) + "." +
//                                           (0xff & (int)ip[3]));
//                System.out.println();
//            }
//        }
//        catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

        Scanner sc = new Scanner(System.in);
        String fileName = null;

        try (Phone phone = new Phone("127.0.0.1", 8080)) {
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

                System.out.println("response: " + response);
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
        try {
            return new BufferedReader(new InputStreamReader(is, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
