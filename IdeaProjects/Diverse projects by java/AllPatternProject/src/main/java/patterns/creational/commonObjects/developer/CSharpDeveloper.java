package patterns.creational.commonObjects.developer;

public class CSharpDeveloper implements Developer {

    private String code;

    public CSharpDeveloper(String code) {
        this.code = code;
    }

    @Override
    public String writeCode() {
        String code = "CPP CODE";
        String msm = String.format("C# developer write c# code Code: %s", code);
        System.out.println(msm);
        return code;
    }
}
