package run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import run.objects.Monkey;

import javax.naming.*;
import java.util.Enumeration;
import java.io.File;
import java.util.Hashtable;

/**
 * class Launcher was created for learning JNDI.
 * In this class I learnt lookup/createSubcontext/destroySubcontext/list/listBindings.
 */

public class Launcher_2 {

    public static final Logger log = LogManager.getLogger(Referenceable.class.getName());

    public static void main(String[] args) throws NamingException {

        Hashtable<String, String> environment = new Hashtable<>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        environment.put(Context.PROVIDER_URL, "file:/D:/Repository/My projects (programing)/MyProjects.git/IdeaProjects/MVC WF Security Hibernate-ORM-JPA JNDI/testJNDI/src/main/resources");

        Context context = new InitialContext(environment);

        Monkey monkeySteven = new Monkey("Steven", "orange", true);

//        context.rebind("steven", monkeySteven);
//        Object monkeyStevensBrother = context.lookup("steven");

//        2)createSubcontext()/destroySubcontext()
//        context.createSubcontext("new folder");
//        context.destroySubcontext("new folder");

//        3)list()
//        Enumeration<NameClassPair> data = context.list(""); //parameter = "" , because we take current context ("context")
//
//        while(data.hasMoreElements()){
//            NameClassPair ncp = data.nextElement();
//            System.out.format("%s | %s%n", ncp.getName(), ncp.getClassName());
//        }

//        4)listBindings()
//        NamingEnumeration<Binding> bindingEnumeration = context.listBindings("");//parameter = "" , because we take current context ("context")
//
//        while(bindingEnumeration.hasMore()) {
//            Binding binding = bindingEnumeration.next();
//
//            System.out.format("%s | %s", binding.getName(), binding.getClassName());
//
//            if(binding.getObject() instanceof Monkey) {
//                System.out.format(" | size %s", ((Monkey) binding.getObject()));
//            }
//            else if (binding.getObject() instanceof File) {
//                System.out.format(" | size %s", ((File) binding.getObject()).length());
//            }
//
//            System.out.println();
//        }

    }
}
