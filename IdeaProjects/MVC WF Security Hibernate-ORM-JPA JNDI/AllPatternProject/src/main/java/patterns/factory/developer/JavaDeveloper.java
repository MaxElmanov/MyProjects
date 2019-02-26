package patterns.factory.developer;

public class JavaDeveloper implements Developer {

    private String code;

    public JavaDeveloper(String code) {
        this.code = code;
    }

    @Override
    public void writeCode() {
        System.out.println("Java developer write java code...");
    }
}
