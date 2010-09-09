package com.bawi.addition2;

abstract class Node {
    Node right;
    Node left;
    int value;
}

class ValueNode extends Node {
    public ValueNode(int value) {
        this.value = value;
    }
}

abstract class OpNode extends Node {
    char operation;
}

class PlusOpNode extends Node {
    char operation;

    public PlusOpNode(Node right, Node left) {
        value = right.value + left.value;
    }
}

class MultiOpNode extends Node {
    char operation;

    public MultiOpNode(Node right, Node left) {
        value = right.value * left.value;
    }
}

public class RunExample2 {
    public static void main(String[] args) {
        int value = new PlusOpNode(new ValueNode(1), new MultiOpNode(new ValueNode(2), new ValueNode(3))).value;
        System.out.println(value);
    }
}
