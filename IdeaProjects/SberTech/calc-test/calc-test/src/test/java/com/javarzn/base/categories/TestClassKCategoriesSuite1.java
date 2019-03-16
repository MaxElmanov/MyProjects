package com.javarzn.base.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

interface FastTests {}
interface SlowTests {}

@RunWith(Categories.class)
@Categories.IncludeCategory(SlowTests.class)
@Suite.SuiteClasses( { TestClassKCategoriesA.class, TestClassKCategoriesB.class }) // Note that Categories is a kind of Suite
public class TestClassKCategoriesSuite1 {
    // Will run A.b and B.c, but not A.a
}

