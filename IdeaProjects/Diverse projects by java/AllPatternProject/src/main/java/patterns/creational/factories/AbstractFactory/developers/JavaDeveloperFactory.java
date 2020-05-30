package patterns.creational.factories.AbstractFactory.developers;

import patterns.creational.commonObjects.developer.Developer;
import patterns.creational.commonObjects.developer.JavaDeveloper;

public class JavaDeveloperFactory implements DeveloperFactory
{
    public Developer getDeveloper()
    {
        return new JavaDeveloper("java code");
    }
}
