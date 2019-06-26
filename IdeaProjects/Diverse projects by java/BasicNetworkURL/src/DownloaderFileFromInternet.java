import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DownloaderFileFromInternet {
    public static void main(String[] args) {

        String urlPath = "http://www.google.com";
        String fileName = "ResultFile.txt";

        URL url = null;
        URLConnection urlConnection = null;
        InputStream is = null;

        FileOutputStream fos = null;

        try{

            url = new URL(urlPath);
            urlConnection = url.openConnection();
            is = urlConnection.getInputStream();

            fos = new FileOutputStream(fileName);

            int data = 0;
            while((data = is.read()) != -1) {
                fos.write(data);
            }
        }
        catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
        finally {
            try {
                is.close();
                fos.flush();
                fos.close();

                System.out.println("you've successfully downloaded data into file - \"" + fileName + "\" from url - \"" + urlPath + "\"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
