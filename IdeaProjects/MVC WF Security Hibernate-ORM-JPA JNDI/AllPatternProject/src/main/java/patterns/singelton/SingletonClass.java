package patterns.singelton;

public class SingletonClass {

    private SingletonClass(){}

    private static class Inner{
        private static SingletonClass instance = new SingletonClass();
    }
    
    public static SingletonClass getInstance(){
        return Inner.instance;
    }
}
