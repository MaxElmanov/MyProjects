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
            File file = new File("src\\main\\resources\\" + fileName);
            conninfo = mapper.readValue(file, ConnectionInfo.class);
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

//    public static void main(String[] args) {
//        JSONExecuter JSON = new JSONExecuter();
////        ConnectionInfo conninfo = null;
////        conninfo = new ConnectionInfo();
////        conninfo.setHost("localhost");
////        conninfo.setPort(1414);
////        conninfo.setChannel("SYSTEM.DEF.SVRCONN");
////        conninfo.setQueueManagerName("IBMESBQM1");
////        conninfo.setQueueName("IBM.ESB.IN");
////        JSON.write("consumer.json", conninfo);
//
//        ConnectionInfo conninfo = JSON.read("producer.json");
//
//        System.out.println(conninfo);
//    }
}
