package main.event;

import main.MyMain;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MathRegister.class)
public class MathTest
{
    private static MyMain myMain;

    @BeforeClass
    public static void prepare()
    {
        myMain = new MyMain();
    }

    @Test
    public void makeTest1()
    {
        int actual = myMain.sum(1, 2);
        int expected = 3;
        Assert.assertEquals(expected, actual);
    }

    @Test
    @Ignore
    public void makeTest2()
    {
        int actual = myMain.sum(2, 3);
        int expected = 5;
        Assert.assertEquals(expected, actual);
    }

    public void makeTest3()
    {
        Assert.fail("manually fail");
    }

    @Test(expected = NullPointerException.class)
    public void makeTest4()
    {
        myMain = null;
        int actual = myMain.sum(4, 5);
        int expected = 9;
        Assert.assertEquals(expected, actual);
    }
}
