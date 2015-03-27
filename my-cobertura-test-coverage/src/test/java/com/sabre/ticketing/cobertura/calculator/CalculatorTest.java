package com.sabre.ticketing.cobertura.calculator;


import org.junit.Assert;
import org.junit.Test;

import com.sabre.ticketing.cobertura.calculator.Calculator;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Assert.assertEquals(3, new Calculator().calculate('+', 1, 2));
    }

}
