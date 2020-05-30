package patterns.creational.factories.FactoryMethod;

import patterns.creational.commonObjects.developer.Developer;

public class main {
    public static void main(String[] args) {
        Developer javaDeveloper = DeveloperFactory.getDeveloper("java", "System.out,println(...);");
        Developer cppDeveloper = DeveloperFactory.getDeveloper("cpp", "cout<<...");
        Developer cSharpDeveloper = DeveloperFactory.getDeveloper("csharp", "writeLine(...);");

        javaDeveloper.writeCode();
        cppDeveloper.writeCode();
        cSharpDeveloper.writeCode();

        Developer nobody = DeveloperFactory.getDeveloper("bal-bla", "...");
        nobody.writeCode();
    }
}
