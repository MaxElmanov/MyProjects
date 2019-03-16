package com.javarzn.data;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeThat;

public class JAssume {
    @Test
    public void filenameIncludesUsername() {
       assumeThat(File.separatorChar, is('\\'));
       assumeThat(File.separatorChar, is('/'));
       assertThat("configfiles/optimus.cfg", is("configfiles/optimus.cfg"));
    }
}
