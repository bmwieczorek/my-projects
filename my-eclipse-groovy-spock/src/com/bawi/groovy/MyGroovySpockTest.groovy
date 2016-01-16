package com.bawi.groovy
import static org.junit.Assert.*
import spock.lang.Specification

class MyGroovySpockTest extends Specification { // eclipse run as JUnit test

        def "adding an element leads to size increase"() {  
        setup: "a new stack instance is created"        
            def stack = new Stack()

        when:                                           
            stack.push 42

        then:                                           
            stack.size() == 1
    }
}