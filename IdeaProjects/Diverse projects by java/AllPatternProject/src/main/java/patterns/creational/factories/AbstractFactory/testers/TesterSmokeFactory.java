package patterns.creational.factories.AbstractFactory.testers;

import patterns.creational.commonObjects.tester.Tester;
import patterns.creational.commonObjects.tester.TesterSmoke;

public class TesterSmokeFactory implements TesterFactory
{
    @Override
    public Tester getTester()
    {
        return new TesterSmoke();
    }
}
