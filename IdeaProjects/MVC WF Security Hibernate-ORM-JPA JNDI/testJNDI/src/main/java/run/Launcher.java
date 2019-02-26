package run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.*;
import javax.naming.Context;
import javax.security.auth.Refreshable;
import java.util.Hashtable;
import java.util.Objects;

public class Launcher implements Referenceable {

    public static final Logger logger = LogManager.getLogger(Referenceable.class.getName());

    private String name = "it's my super launcher";

    public static void main(String[] args) throws NamingException {

        String name = "C:\\Users\\MaxNick\\IdeaProjects\\MVC WF Security Hibernate-ORM-JPA JNDI\\testJNDI\\src\\main\\resources\\text.txt";

        Hashtable<String, String> environment =  new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        environment.put(Context.PROVIDER_URL, "file:/D:/");

        Context context = new InitialContext(environment);

        try {
            Object launcher = context.lookup("Launcher");
            logger.info("\n---------\n"+launcher+"\n----------");
            if(Objects.nonNull(launcher)){
                logger.info(((Launcher) launcher).name);
            }
        }
        catch (Exception e){
            logger.error("NameAlreadyBoundException " + e.getMessage());
        }
    }

    public Reference getReference() throws NamingException {
        Reference reference = new Reference(Launcher.class.getName());

        reference.add(new StringRefAddr("name", this.name));

        return reference;
    }
}
