package com.bawi.junit.assume;

import org.junit.Assume;
import org.junit.Test;

public class JunitAssumeSampleTest {
    
    @Test
    public void shouldSkipTest() {
        boolean isIntegrationEnvironment = false;

        // assume
        Assume.assumeTrue("This test should only run in integration environment", isIntegrationEnvironment);

        // this code will NOT be executed
        throw new RuntimeException();
    }

    @Test(expected = RuntimeException.class)
    public void shouldRunTestTest() {
        boolean isIntegrationEnvironment = true;

        // assume
        Assume.assumeTrue("This test should only run in integration environment", isIntegrationEnvironment);

        // this code will be executed
        throw new RuntimeException();
    }
}
