import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Launcher {
    public static void main(String[] args) throws NamingException, JMSException {

        /* Псевдоним менеджера из файла .bindings */
        String qFactoryName = "QM_Transfer";
        /* Псевдоним очереди из файла .bindings */
        String qName = "test";
        /* Задаются свойства контекста */
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        env.put(Context.PROVIDER_URL, "file://D:\\Repository\\My projects (programing)\\MyProjects.git\\IdeaProjects\\Diverse projects by java\\WebSphereMQProject\\.bindings");
        /* Создается контекст */
        Context ctx = new InitialContext(env);
        /* Создаются фабрика и очередь */
        QueueConnectionFactory factory =
                (QueueConnectionFactory) ctx.lookup(qFactoryName);
        Queue queue = (Queue) ctx.lookup(qName);
        System.out.println("queue name = " + queue.getQueueName());
        ctx.close();
    }
}
