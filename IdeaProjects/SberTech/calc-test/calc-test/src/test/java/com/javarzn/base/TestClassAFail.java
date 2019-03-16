package com.javarzn.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * Пример проваленного теста.
 */
public class TestClassAFail {
    /**
     * Этот тест не будет выполнен.
     */
    @Test
    public void testMyMethod() {
        Assert.fail();
    }
}
