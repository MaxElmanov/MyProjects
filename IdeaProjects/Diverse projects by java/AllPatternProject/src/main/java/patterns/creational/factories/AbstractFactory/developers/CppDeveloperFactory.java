package patterns.creational.factories.AbstractFactory.developers;

import patterns.creational.commonObjects.developer.CppDeveloper;
import patterns.creational.commonObjects.developer.Developer;

public class CppDeveloperFactory implements DeveloperFactory
{
    @Override
    public Developer getDeveloper()
    {
        return new CppDeveloper("Cpp code");
    }
}
