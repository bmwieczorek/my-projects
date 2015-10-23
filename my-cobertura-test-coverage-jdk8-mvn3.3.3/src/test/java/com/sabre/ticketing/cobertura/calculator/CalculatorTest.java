package com.sabre.ticketing.cobertura.calculator;


import org.junit.Assert;
import org.junit.Test;

import com.sabre.ticketing.cobertura.calculator.Calculator;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Assert.assertEquals(3, new Calculator().calculate('+', 1, 2));

    }

    @Test
    public void testSubstract() {
        Assert.assertEquals(-1, new Calculator().calculate('-', 1, 2));

    }

//    @Test(expected=IllegalArgumentException.class)
//    public void testMultiply() {
//        Assert.assertEquals(-1, new Calculator().calculate('*', 1, 2));
//    }
}
