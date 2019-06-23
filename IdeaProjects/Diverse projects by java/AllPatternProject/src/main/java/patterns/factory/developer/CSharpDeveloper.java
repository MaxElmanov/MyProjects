package patterns.factory.developer;

public class CSharpDeveloper implements Developer {

    private String code;

    public CSharpDeveloper(String code) {
        this.code = code;
    }

    @Override
    public void writeCode() {
        System.out.println("C# developer write c# code");
        System.out.println("Code: \n" + code + "\n-------------------");
    }
}
