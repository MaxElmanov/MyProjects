package patterns.factory;

import patterns.factory.developer.Developer;

public class main {
    public static void main(String[] args) {
        Developer javaDeveloper = DeveloperFactory.getDeveloper("java", "System.out,println(...);");
        Developer cppDeveloper = DeveloperFactory.getDeveloper("cpp", "cout<<...");
        Developer cSharpDeveloper = DeveloperFactory.getDeveloper("csharp", "writeLine(...);");

        javaDeveloper.writeCode();
        cppDeveloper.writeCode();
        cSharpDeveloper.writeCode();

//        my RuntimeException is in DeveloperFactory.getDeveloper method.
//
//        Developer nobody = DeveloperFactory.getDeveloper("bal-bla", "...");
//        nobody.writeCode();
    }
}
