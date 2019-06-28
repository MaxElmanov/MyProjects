package com.ibm.mq.jms.json.objects;

import java.io.Serializable;

public class ConnectionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String   host            ;
    private int      port            ;
    private String   channel         ;
    private String   queueManagerName;
    private String   queueName       ;

    public ConnectionInfo() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getQueueManagerName() {
        return queueManagerName;
    }

    public  void setQueueManagerName(String queueManagerName) {
        this.queueManagerName = queueManagerName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public String toString() {
        return  "host: " + host + "\n" +
                "port: " + port + "\n" +
                "channel: " + channel + "\n" +
                "queueManagerName: " + queueManagerName + "\n" +
                "queueName: " + queueName + "\n";

    }
}
