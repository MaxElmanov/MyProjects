package com.ibm.mq.jms.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.mq.jms.objects.ConnectionInfo;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class JSONExecuter
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();

    public ConnectionInfo read(String fileName)
    {
        URI uri = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            uri = this.getClass().getClassLoader().getResource(fileName).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String path = Paths.get(uri).toString().replace("\\", "\\\\");

        try {
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);

            int ch = 0;
            while (true) {
                ch = bis.read();
                if (ch == -1) {
                    break;
                }
                stringBuffer.append((char) ch);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String gson = stringBuffer.toString();
        ConnectionInfo connectionInfo = GSON.fromJson(gson, ConnectionInfo.class);
        return connectionInfo;
    }

    public void write(String fileName, ConnectionInfo conninfo)
    {
        String gson = GSON.toJson(conninfo);

        URI uri = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            uri = this.getClass().getClassLoader().getResource(fileName).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String path = Paths.get(uri).toString().replace("\\", "\\\\");

        try {
            fos = new FileOutputStream(path);
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write(gson);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    bw.close();
                    osw.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
