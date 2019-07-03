import org.junit.*;

public class RunTest
{
    @Test
    public void getTest(){
        String str = new Run().getMessage();
        System.out.println("Message: " + str);
        Assert.assertEquals("2 b || ! 2 b", str);
    }
}
