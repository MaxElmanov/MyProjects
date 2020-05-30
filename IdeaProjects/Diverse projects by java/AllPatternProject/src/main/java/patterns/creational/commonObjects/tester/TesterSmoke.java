package patterns.creational.commonObjects.tester;

public class TesterSmoke implements Tester
{
    @Override
    public void test(String code)
    {
        System.out.println("SMOKE tester tested code: " + code);
    }
}
