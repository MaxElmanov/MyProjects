package com.ibm.mq.jms.json;

import com.ibm.mq.jms.json.objects.ConnectionInfo;
import java.io.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JSONExecuter {

    public ConnectionInfo read(String fileName){
        ObjectMapper mapper = new ObjectMapper();
        ConnectionInfo conninfo = null;
        try {
            InputStream resourceAsStream = JSONExecuter.class.getClassLoader().getResourceAsStream(fileName);
            conninfo = mapper.readValues(resourceAsStream, ConnectionInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return conninfo;
        }
    }

    public void write(String fileName, ConnectionInfo conninfo){
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File("src\\main\\resources\\" + fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, conninfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
