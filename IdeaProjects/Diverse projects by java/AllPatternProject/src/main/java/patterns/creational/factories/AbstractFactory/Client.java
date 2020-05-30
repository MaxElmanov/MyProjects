package patterns.creational.factories.AbstractFactory;

import patterns.creational.commonObjects.developer.Developer;
import patterns.creational.commonObjects.tester.Tester;
import patterns.creational.factories.AbstractFactory.developers.DeveloperFactory;
import patterns.creational.factories.AbstractFactory.testers.TesterFactory;

public class Client
{
    public void doSomeOperation(DeveloperFactory developerFactory, TesterFactory testerFactory){
        Developer developer = developerFactory.getDeveloper();
        Tester tester = testerFactory.getTester();

        //developer writes code
        String code = developer.writeCode();

        //tester tests it
        tester.test(code);
    }
}
