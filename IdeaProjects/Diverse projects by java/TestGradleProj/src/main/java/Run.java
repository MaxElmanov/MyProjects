import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.io.*;

public class Run
{
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("I'm the main project");

            Properties props = new Properties();
        try {
            props.load(new BufferedReader(new FileReader(new File(".\\config\\properties.ini"))));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(props.getProperty("InputDir"));
        System.out.println(props.getProperty("NoReceived"));
        System.out.println(props.getProperty("Numb"));

    }
}

