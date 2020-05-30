package patterns.creational.factories.AbstractFactory;

import patterns.creational.factories.AbstractFactory.developers.CppDeveloperFactory;
import patterns.creational.factories.AbstractFactory.developers.JavaDeveloperFactory;
import patterns.creational.factories.AbstractFactory.testers.TesterModuleFactory;
import patterns.creational.factories.AbstractFactory.testers.TesterSmokeFactory;

public class main
{
    public static void main(String[] args)
    {
        Client client = new Client();

        client.doSomeOperation(new JavaDeveloperFactory(), new TesterModuleFactory());
        client.doSomeOperation(new CppDeveloperFactory(), new TesterSmokeFactory());
    }
}
