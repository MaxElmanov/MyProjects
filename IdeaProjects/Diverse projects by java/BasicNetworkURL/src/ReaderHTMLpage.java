import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReaderHTMLpage {
    public static void main(String[] args) {

        String nextString = null;
        URL url = null;
        URLConnection urlConnection = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {

            url = new URL("http://www.google.com");
            urlConnection = url.openConnection();
            isr = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
            br = new BufferedReader(isr);

            while(true){
                nextString = br.readLine();
                System.out.println(nextString);
                if(nextString == null) {
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println("catch");
        }
        finally {
            try {
                isr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
