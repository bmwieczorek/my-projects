package javatest;

public class TestOverloadNumbers {
    public static void print(Float a, double b) {
        System.out.println("1");
    }

    public static void print(double a, double b) {
        System.out.println("2");
    }

    public static void print(float a, int b) {
        System.out.println("3");
    }

    public static void print(int a, float b) {
        System.out.println("4");
    }

    public static void print(double a, int b) {
        System.out.println("5");
    }

    public static void main(String args[]) {
        TestOverloadNumbers.print(2, 3.0);
    }
}