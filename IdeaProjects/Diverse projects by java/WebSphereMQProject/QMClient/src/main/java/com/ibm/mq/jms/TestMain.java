package com.ibm.mq.jms;

import java.io.*;

public class TestMain {
    public static void main(String[] args) throws IOException {

        StringBuffer string = new StringBuffer();

        InputStream in = TestMain.class.getClassLoader().getResourceAsStream("consumer.json");
        InputStreamReader ois = new InputStreamReader(in, "UTF-8");
        BufferedReader bis = new BufferedReader(ois);

        String str = null;
        while((str = bis.readLine()) != null) {
            System.out.println(str);
            string.append(str);
        }

        String strresult = string.toString();
        System.out.println(strresult);

    }
}
