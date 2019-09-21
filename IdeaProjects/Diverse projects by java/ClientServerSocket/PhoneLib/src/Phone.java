import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Phone implements Closeable
{
    public static final String LAST_LINE = "--END--";
    public static final String CRLF = "\r\n";

    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;

    public Phone(String ip, int port)
    {
        try {
            socket = new Socket(ip, port);
            reader = createReader();
            writer = createWriter();
        }
        catch (IOException e) {
            //TIMEOUT EXCEPTION
            e.printStackTrace();
        }
    }

    public Phone(ServerSocket serverSocket, List<String> hostNames)
    {
        try {
            socket = createSocket(serverSocket, hostNames);
            writer = createWriter();
            reader = createReader();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedWriter createWriter() throws IOException
    {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    private BufferedReader createReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private Socket createSocket(ServerSocket serverSocket, List<String> hostNames) throws IOException
    {
        Socket socket = serverSocket.accept();
        List<String> ipAddresses = fillUpValidIpAddresses(hostNames);

        if (checkIpAddress(ipAddresses, socket)) {
            return socket;
        }
        else {
            System.out.println("\033[32m Current host address (" + socket.getInetAddress()
                    .getHostAddress() + ") is not valid for the server \033[0m");
            System.exit(-1);
        }

        return null;
    }

    private boolean checkIpAddress(List<String> ipAddresses, Socket socket)
    {
        return ipAddresses.contains(socket.getLocalAddress().getHostAddress())
                || ipAddresses.contains(socket.getInetAddress().getHostAddress());
    }

    private static List<String> fillUpValidIpAddresses(List<String> hostNames)
    {
        InetAddress[] ia = null;
        List<String> ipAddresses = new ArrayList<>();

        try {
            for (int i = 0; i < hostNames.size(); i++) {
                ia = InetAddress.getAllByName(hostNames.get(i));

                for (int j = 0; j < ia.length; j++) {
                    ipAddresses.add(ia[j].getHostAddress());
                }
            }

            return ipAddresses;
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeLine(String message)
    {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String readLine()
    {
        try {
            return reader.readLine();
        }
        catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public void close() throws IOException
    {
        reader.close();
        writer.close();
        socket.close();
    }
}
