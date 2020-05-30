package patterns.creational.commonObjects.developer;

public class CppDeveloper implements Developer {

    private String code;

    public CppDeveloper(String code) {
        this.code = code;
    }

    @Override
    public String writeCode() {
        String code = "C++ CODE";
        String msm = String.format("C++ developer write c++ code Code: %s", code);
        System.out.println(msm);
        return code;
    }
}
