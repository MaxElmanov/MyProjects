package run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Referenceable;
import java.util.Hashtable;
import java.io.File;

/**
 * class Launcher was created for learning JNDI.
 * In this class I learnt composeName().
 */

public class launcher_3 {
    public static final Logger log = LogManager.getLogger(Referenceable.class.getName());

    public static void main(String[] args) throws NamingException {

        Hashtable<String, String> environment = new Hashtable<>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        environment.put(Context.PROVIDER_URL, "file:/D:/Repository/My projects (programing)/MyProjects.git/IdeaProjects/MVC WF Security Hibernate-ORM-JPA JNDI/testJNDI/src/main/resources");

        Context context = new InitialContext(environment);

        Context folder_1 = (Context) context.lookup("folder_1");

        String name = folder_1.composeName("inner_folder_1", "folder_1");
        System.out.println(name);

        Context inner_folder_1 = (Context) folder_1.lookup("inner_folder_1");
        name = inner_folder_1.composeName("text_1.txt", name);
        System.out.println(name);

        System.out.println(context.lookup(name) instanceof File);
    }
}
