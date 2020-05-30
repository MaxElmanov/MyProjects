package patterns.creational.factories.FactoryMethod;

import patterns.creational.commonObjects.developer.CSharpDeveloper;
import patterns.creational.commonObjects.developer.CppDeveloper;
import patterns.creational.commonObjects.developer.Developer;
import patterns.creational.commonObjects.developer.JavaDeveloper;

public class DeveloperFactory {
    public static Developer getDeveloper(String typeDeveloper, String code){

        if(typeDeveloper.equalsIgnoreCase("java")){
            return new JavaDeveloper(code);
        }
        else if(typeDeveloper.equalsIgnoreCase("C#") || typeDeveloper.equalsIgnoreCase("CSharp")){
            return new CSharpDeveloper(code);
        }
        else if(typeDeveloper.equalsIgnoreCase("C++") || typeDeveloper.equalsIgnoreCase("CPP")) {
            return new CppDeveloper(code);
        }
        else{
            throw new RuntimeException(typeDeveloper + " is unknown type developer.");
        }

    }
}
