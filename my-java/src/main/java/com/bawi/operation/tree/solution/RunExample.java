package com.bawi.operation.tree.solution;

import static java.lang.String.valueOf;

abstract class Node {
    abstract int evaluate();
}

class ValueNode extends Node {
    int value;

    public ValueNode(int value) {
        this.value = value;
    }

    int evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return valueOf(value);
    }
}

abstract class OpNode extends Node {
    Node right;
    Node left;
    char operation;

    public OpNode(Node right, Node left) {
        this.right = right;
        this.left = left;
    }

    @Override
    public String toString() {
        return "(" + right + operation + left + ")";
    }

}

class PlusOpNode extends OpNode {
    public PlusOpNode(Node right, Node left) {
        super(right, left);
        operation = '+';
    }

    int evaluate() {
        return right.evaluate() + left.evaluate();
    }
}

class MultiOpNode extends OpNode {
    public MultiOpNode(Node right, Node left) {
        super(right, left);
        operation = '*';
    }

    int evaluate() {
        return right.evaluate() * left.evaluate();
    }
}

public class RunExample {
    public static void main(String[] args) {
        PlusOpNode node = new PlusOpNode(new ValueNode(1),
                new MultiOpNode(new ValueNode(2), new ValueNode(3)));
        System.out.println(node);
        System.out.println(node.evaluate());
    }
}
