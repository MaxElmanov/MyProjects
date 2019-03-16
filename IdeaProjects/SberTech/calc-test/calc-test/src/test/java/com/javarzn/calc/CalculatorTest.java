package com.javarzn.calc;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private static Calculator calculator;

    @BeforeClass
    public static void init(){
        calculator = new Calculator();
    }

    @Test
    public void testSummarize() {
        long actual = calculator.summarize(1,-4);
        long expected = -2;
        assertEquals("'testSummarize' method has error !", expected, actual);
    }

    @Test
    public void testSubtract() {
        long actual = calculator.subtract(1,4);
        long expected = -3;
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiply() {
        long actual = calculator.multiply(1,4);
        long expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void testDivide() {
        long actual = calculator.divide(16,4);
        long expected = 4;
        assertEquals(expected, actual);
    }
}