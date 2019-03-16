package com.javarzn.base;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Обратите внимание на эту аннотацию, она, а вернее её наличие делает возможным такое выполнение теста.
 * Если её закомментировать то результат может как измениться так и остаться прежним, проверим у кого как.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClassDMethodOrder {

    @Test
    public void testA() {
        System.out.println("first");
    }

    @Test
    public void testB() {
        System.out.println("second");
    }

    @Test
    public void testC() {
        System.out.println("third");
    }
}
