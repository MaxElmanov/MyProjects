package run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import run.objects.Monkey;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Referenceable;
import java.util.Hashtable;

public class Launcher{

    public static final Logger logger = LogManager.getLogger(Referenceable.class.getName());

    public static void main(String[] args) throws NamingException {

        Hashtable<String, String> environment = new Hashtable<>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        environment.put(Context.PROVIDER_URL, "file:/D:/Repository/My projects (programing)/MyProjects.git/IdeaProjects/MVC WF Security Hibernate-ORM-JPA JNDI/testJNDI/src/main/resources");

        Context context = new InitialContext(environment);

        Monkey monkeySteven = new Monkey("Steven", "orange", true);
        Monkey monkeyAlex = new Monkey("Alex", "tomato", false);

        context.rebind("monkeySteven", monkeySteven);
        context.rebind("monkeyAlex", monkeyAlex);

        context.unbind("monkeySteven");
    }
}
