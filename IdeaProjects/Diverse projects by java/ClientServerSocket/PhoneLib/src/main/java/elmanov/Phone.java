package elmanov;

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
            this.reader = this.createReader();
            this.writer = this.createWriter();
        }
        catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    public Phone(ServerSocket serverSocket)
    {
        try {
            this.socket = this.createSocket(serverSocket);
            this.writer = this.createWriter();
            this.reader = this.createReader();
        }
        catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    private BufferedWriter createWriter() throws IOException
    {
        return new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
    }

    private BufferedReader createReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    private Socket createSocket(ServerSocket serverSocket) throws IOException
    {
        return serverSocket.accept();
    }

    public void writeLine(String message)
    {
        try {
            this.writer.write(message);
            this.writer.newLine();
            this.writer.flush();
        }
        catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public String readLine()
    {
        try {
            return this.reader.readLine();
        }
        catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public void close() throws IOException
    {
        this.reader.close();
        this.writer.close();
        this.socket.close();
    }
}
