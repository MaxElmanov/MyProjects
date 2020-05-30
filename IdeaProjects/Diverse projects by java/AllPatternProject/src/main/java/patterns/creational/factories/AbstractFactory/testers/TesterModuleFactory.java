package patterns.creational.factories.AbstractFactory.testers;

import patterns.creational.commonObjects.tester.Tester;
import patterns.creational.commonObjects.tester.TesterModule;

public class TesterModuleFactory implements TesterFactory
{
    @Override
    public Tester getTester()
    {
        return new TesterModule();
    }
}
