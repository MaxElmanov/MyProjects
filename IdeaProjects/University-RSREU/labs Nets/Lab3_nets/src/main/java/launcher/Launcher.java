package launcher;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Launcher
{
    public static void main(String[] args) throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection)new URL("file:///D:/Repository/My%20projects%20(programing)/MyProjects.git/IdeaProjects/University-RSREU/labs%20Nets/Lab3_nets/site/index.html").openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Prop_1", "Prop_Value_1");

        for (Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
