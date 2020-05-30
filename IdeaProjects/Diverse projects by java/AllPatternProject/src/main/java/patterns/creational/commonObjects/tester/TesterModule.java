package patterns.creational.commonObjects.tester;

public class TesterModule implements Tester
{
    @Override
    public void test(String code)
    {
        System.out.println("MODULE tester tested code: " + code);
    }
}
