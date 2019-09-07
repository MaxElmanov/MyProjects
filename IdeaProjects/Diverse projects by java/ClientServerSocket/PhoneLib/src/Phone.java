import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
            this.socket = new Socket(ip, port);
            this.reader = createReader();
            this.writer = createWriter();
        }
        catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    public Phone(ServerSocket serverSocket)
    {
        try {
            this.socket = createSocket(serverSocket);
            this.writer = createWriter();
            this.reader = createReader();
        }
        catch (Exception var3) {
            throw new RuntimeException(var3);
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

    private Socket createSocket(ServerSocket serverSocket) throws IOException
    {
        return serverSocket.accept();
    }

    public void writeLine(String message)
    {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        }
        catch (Exception var3) {
            throw new RuntimeException(var3);
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
