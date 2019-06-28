package com.ibm.mq.jms.logics;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import javax.jms.Connection;
import javax.jms.JMSException;

public abstract class JmsBase {
    protected Connection connection  = null;
    //---------------------------------------------------------------
    private void createConnection(String host, int port, String channel, String queueManagerName)
    {
        JmsFactoryFactory    ff;
        JmsConnectionFactory cf;
        try {
            ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
            cf = ff.createConnectionFactory();

            // Set the properties
            cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, host   );
            cf.setIntProperty   (WMQConstants.WMQ_PORT     , port   );
            cf.setStringProperty(WMQConstants.WMQ_CHANNEL  , channel);
            cf.setIntProperty   (WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_BINDINGS);
            cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER,   queueManagerName);

            connection  = cf.createConnection();
        } catch (JMSException jmsex) {
            recordFailure(jmsex);
        }
    }
    //---------------------------------------------------------------
    public JmsBase(String host, int port, String channel, String queueManagerName)
    {
            createConnection(host, port, channel, queueManagerName);
    }
    //---------------------------------------------------------------
    protected void recordFailure(Exception ex){
        if(ex != null){
            if(ex instanceof JMSException) {
                processJMSException((JMSException) ex);
            }
            else{
                System.out.println(ex);
            }
        }

        System.out.println("FAILURE");
    }
    //---------------------------------------------------------------
    protected void processJMSException(JMSException jmse) {
        System.out.println(jmse);
        Throwable innerException = jmse.getLinkedException();
        if(innerException != null) {
            System.out.println("innerException(s): ");
        }
        while(innerException != null) {
            System.out.println(innerException);
            innerException = innerException.getCause();
        }

        return;
    }
}
