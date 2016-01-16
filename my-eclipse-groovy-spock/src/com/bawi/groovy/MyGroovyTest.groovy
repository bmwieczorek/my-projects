package com.bawi.groovy
import static groovy.test.GroovyAssert.shouldFail

import org.junit.Test


class MyGroovyTest { // run a JUnit test

    @Test
    void indexOutOfBoundsAccess() {
        def numbers = [1,2,3,4]
        shouldFail {
            numbers.get(4)
        }
    }
    
    @Test
    void testSum() {
        def a = 2, b = 2
        assert a == b
    }

}
