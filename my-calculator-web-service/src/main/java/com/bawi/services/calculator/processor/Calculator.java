package com.bawi.services.calculator.processor;

import java.util.List;

import com.bawi.services.calculator.model.Operation;

public class Calculator {

    public int calculate(Operation operation, List<Integer> parameters) {
        switch (operation) {
        case ADD:
            return add(parameters);

        default:
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    private static int add(List<Integer> parameters) {
        int result = 0;
        for (int i : parameters) {
            result += i;
        }
        return result;
    }
}
