package main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MyMainTest {

    private int a;
    private int b;
    private int expected;

    public MyMainTest(int a, int b, int expected) {
        this.a = a;
        this.b = b;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        Object[][] list = new Object[][]{
                {1,  2, 3},
                {2, -45, -43},
                {-0, -19, -19},
                {+0, -19, -19}
        };

        return Arrays.asList(list);
    }

    @Test
    public void test1() {
        int actual = MyMain.sum(a, b);
        assertEquals(expected, actual);
    }
}