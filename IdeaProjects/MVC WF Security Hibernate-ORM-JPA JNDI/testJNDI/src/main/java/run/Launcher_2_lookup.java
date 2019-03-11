package run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import run.objects.Monkey;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Referenceable;
import java.util.Hashtable;



public class Launcher_2_lookup {

    public static final Logger log = LogManager.getLogger(Referenceable.class.getName());

    public static void main(String[] args) throws NamingException {

        Hashtable<String, String> environment = new Hashtable<>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        environment.put(Context.PROVIDER_URL, "file:/D:/Repository/My projects (programing)/MyProjects.git/IdeaProjects/MVC WF Security Hibernate-ORM-JPA JNDI/testJNDI/src/main/resources");

        Context context = new InitialContext(environment);

        Monkey monkeySteven = new Monkey("Steven", "orange", true);

        context.rebind("steven", monkeySteven);

        Object monkeyStevensBrother = context.lookup("steven");

        log.info(monkeyStevensBrother);

    }
}
