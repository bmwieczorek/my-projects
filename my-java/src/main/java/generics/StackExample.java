package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class StackExample {

    Stack<Number> stack = new Stack<Number>();

    void pushAll(Collection<? extends Number> src) {
        for (Number number : src) {
            stack.push(number);
        }
    }

    void popAll(Collection<? super Number> dst) {
        while (!stack.isEmpty()) {
            dst.add(stack.pop());
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.0, 2.0, 3.0);

        StackExample stackExample = new StackExample();
        stackExample.pushAll(integers);
        stackExample.pushAll(doubles);

        Collection<Object> dst = new ArrayList<Object>(stackExample.stack.size());
        stackExample.popAll(dst);
    }
}
