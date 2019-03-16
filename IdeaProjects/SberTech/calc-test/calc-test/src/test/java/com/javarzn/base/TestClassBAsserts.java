package com.javarzn.base;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.hamcrest.core.CombinableMatcher;
import org.junit.Assert;
import org.junit.Test;

public class TestClassBAsserts {
    @Test
    public void testAssertNull() {
        assertNull("should be null", null);
    }

    @Test
    public void testAssertNotNull() {
        assertNotNull("should not be null", new Object());
    }

    @Test
    public void testAssertTrue() {
        assertTrue("failure - should be true", true);
    }

    @Test
    public void testAssertFalse() {
        assertFalse("failure - should be false", false);
    }

    /**
     * Остановамся на аргументах этого метода.
     */
    @Test
    public void testAssertEquals() {
        assertEquals("failure - strings are not equal", "text", new StringBuilder("text").toString());
    }

    @Test
    public void testAssertNotEquals() {
        assertNotEquals("numbers must not be equals !", 12, 45);
    }

    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(768);
        Integer bNumber = aNumber;
        assertSame("should be same", aNumber, bNumber);
    }

    @Test
    public void testAssertNotSame() {
        assertNotSame("should not be same Object", new Object(), new Object());
    }

    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial".getBytes();
        assertArrayEquals("failure - byte arrays not same", expected, actual);
    }

    /**
     * После просмотра всех тестов измените значения локальных переменных.
     */
    @Test
    public void testMyMethod() {
        Assert.assertThat(x, is(3));
        Assert.assertThat(x, is(not(4)));
        Assert.assertThat(responseString, either(containsString("color")).or(containsString("colour")));
    }

    @Test
    public void testAssertThatBothContainsString() {
        assertThat("albumen",  both(containsString("a")).and(containsString("b")));
    }

    @Test
    public void testAssertThatHasItems() {
        assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
    }

    @Test
    public void testAssertThatEveryItemContainsString() {
        assertThat(Arrays.asList(new String[] { "fun", "ban", "net" }),  everyItem(containsString("n")));
    }

    // Core Hamcrest Matchers with assertThat
    @Test
    public void testAssertThatHamcrestCoreMatchers() {
        assertThat("good", allOf(equalTo("good"), startsWith("good")));
        assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
        assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
        assertThat(7, not(CombinableMatcher.<Integer> either(equalTo(3)).or(equalTo(4))));
        assertThat(new Object(), not(sameInstance(new Object())));
    }

    private int x = 3;
    private String responseString = "color_colour";
}
