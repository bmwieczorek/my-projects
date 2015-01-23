package com.bawi.regex;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class TestJavaRegexMatchingPattern {

    @Test
    public void shouldTestMatches() throws Exception {
        Assert.assertTrue(Pattern.matches("t2.emd.log.*", "t2.emd.log"));
        Assert.assertTrue(Pattern.matches("t2.emd.log.*", "t2.emd.log.1"));
        Assert.assertTrue(Pattern.matches("t2.emd.log.*", "t2.emd.log.10"));
        Assert.assertTrue("t2.emd.log".matches("t2.emd.log.*"));
        Assert.assertTrue("t2.emd.log.1".matches("t2.emd.log.*"));
        Assert.assertTrue("t2.emd.log.10".matches("t2.emd.log.*"));

    }
}
