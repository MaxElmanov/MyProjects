package patterns.creational.singelton;

public class SingletonClass {

    private SingletonClass(){}

//-----------1-----------------
//    private static class Inner{
//        private static SingletonClass instance = new SingletonClass();
//    }
    
//    public static SingletonClass getInstance(){
//        return Inner.instance;
//    }

//-----------2-----------------
//    private static SingletonClass instance = new SingletonClass();
//
//    public static SingletonClass getInstance(){
//        return instance;
//    }

//-----------3-----------------
    private static SingletonClass instance;

    public static SingletonClass getInstance(){
        if(instance == null) {
            instance = new SingletonClass();
            return instance;
        }

        return instance;
    }

}
