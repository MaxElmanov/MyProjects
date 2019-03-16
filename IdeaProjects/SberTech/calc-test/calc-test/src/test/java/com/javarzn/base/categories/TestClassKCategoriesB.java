package com.javarzn.base.categories;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.fail;

@Category({SlowTests.class, FastTests.class})
public class TestClassKCategoriesB {
    @Test
    public void c() {

    }
}
