package com.javarzn.data;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import static org.junit.Assert.assertTrue;


public class RRuleChain {
    @Rule
    public final TestRule chain = RuleChain
            .outerRule(new LSimpleRule())
            .around(new LSimpleRule())
            .around(new LSimpleRule());

    @Test
    public void example() {
        assertTrue(true);
    }
}
