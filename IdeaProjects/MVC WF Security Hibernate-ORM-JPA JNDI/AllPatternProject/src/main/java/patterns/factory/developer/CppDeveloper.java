package patterns.factory.developer;

public class CppDeveloper implements Developer {

    private String code;

    public CppDeveloper(String code) {
        this.code = code;
    }

    @Override
    public void writeCode() {
        System.out.println("C++ developer write c++ code");
    }
}
