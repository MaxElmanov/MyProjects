package com.javarzn.base;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Запустить класс и убедиться, что наличие ошибки не приводит к падению теста.
 */
public class TestClassGException {
    /**
     * Испортите тест :-)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void empty() {
        new ArrayList<Object>().get(0);
    }
}
