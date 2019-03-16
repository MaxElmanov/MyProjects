package com.javarzn.base;

import org.junit.Test;

public class TestClassFTimeout {

    @Test(timeout=1000)
    public void testWithTimeoutGood() throws Exception{
        Thread.sleep(100);
    }

    @Test(timeout=1000)
    public void testWithTimeoutBad() throws Exception{
        Thread.sleep(10000);
    }

}
