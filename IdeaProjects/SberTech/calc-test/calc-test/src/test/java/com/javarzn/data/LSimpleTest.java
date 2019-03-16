package com.javarzn.data;

import org.junit.Rule;
import org.junit.Test;

public class LSimpleTest {

    @Rule
    public LSimpleRule rule = new LSimpleRule();

    @Test
    public void test() {
        System.out.println("test");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }
}
