package com.bawi.operation.tree.my;

abstract class Node {
    int value;
}

class ValueNode extends Node {
    public ValueNode(int value) {
        this.value = value;
    }
}

class PlusOpNode extends Node {
    public PlusOpNode(Node right, Node left) {
        value = right.value + left.value;
    }
}

class MultiOpNode extends Node {
    public MultiOpNode(Node right, Node left) {
        value = right.value * left.value;
    }
}

public class RunExample2 {
    public static void main(String[] args) {
        PlusOpNode node = new PlusOpNode(new ValueNode(1),
                new MultiOpNode(new ValueNode(2), new ValueNode(3)));
        System.out.println(node.value);
    }
}
