package com.javarzn.base.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SlowTests.class)
@Categories.ExcludeCategory(FastTests.class)
@Suite.SuiteClasses( { TestClassKCategoriesA.class, TestClassKCategoriesB.class }) // Note that Categories is a kind of Suite
public class TestClassKCategoriesSuite2 {
    // Will run A.b, but not A.a or B.c
}

