package com.bawi.operation.tree.draft;

class Node {
    Node right;
    Node left;
    char operation;
    int value;

    public Node(Node right, Node left, char operation) {
        this.right = right;
        this.left = left;
        this.operation = operation;
        this.value = evaluate();
    }

    public Node(int value) {
        this.value = value;
    }

    int evaluate() {
        switch (operation) {
        case '+':
            return right.value + left.value;
        case '*':
            return right.value * left.value;
        default:
            throw new RuntimeException("not implemented operation " + operation);
        }
    }
}

public class RunExample {
    public static void main(String[] args) {
        int value = new Node(new Node(1), new Node(new Node(2), new Node(3), '*'), '+').value;
        System.out.println(value);
    }
}
