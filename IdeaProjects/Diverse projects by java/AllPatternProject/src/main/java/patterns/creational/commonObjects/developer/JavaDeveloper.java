package patterns.creational.commonObjects.developer;

public class JavaDeveloper implements Developer {

    private String code;

    public JavaDeveloper(String code) {
        this.code = code;
    }

    @Override
    public String writeCode() {
        String code = "JAVA CODE";
        String msm = String.format("Java developer write java code... Code: %s", code);
        System.out.println(msm);
        return code;
    }
}
