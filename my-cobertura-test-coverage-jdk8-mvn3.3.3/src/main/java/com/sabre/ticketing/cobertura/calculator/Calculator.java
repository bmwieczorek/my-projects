package com.sabre.ticketing.cobertura.calculator;

public class Calculator {
    
    public int calculate(char operation, int a, int b) {
        if (operation == '+') {
            return add(a, b);
        }
        if (operation == '-') {
            return substract(a, b);
        }   
        throw new IllegalArgumentException("Illegal operation: " + operation);
    }

    public int add(int a, int b) {
        return a + b;
    }
    
    public int substract(int a, int b) {
        return a - b;
    }

}
