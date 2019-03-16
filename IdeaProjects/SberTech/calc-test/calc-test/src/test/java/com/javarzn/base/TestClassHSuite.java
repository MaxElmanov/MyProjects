package com.javarzn.base;

import com.javarzn.base.TestClassFTimeout;
import com.javarzn.base.TestClassGException;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestClassFTimeout.class,
        TestClassGException.class
})
public class TestClassHSuite {
//    класс пуст
}
