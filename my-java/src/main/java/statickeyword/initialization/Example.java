package statickeyword.initialization;

class ClassWithStaticBlock {
    static {
        System.out.println("Static block");
    }

    public ClassWithStaticBlock() {
        System.out.println("Constructor body");
    }

    public static void doSth() {
        System.out.println("Method body");
    }
}

public class Example {

    public static void main(String[] args) {
        // new ClassWithStaticBlock();
        // new ClassWithStaticBlock();

        ClassWithStaticBlock.doSth();
    }
}
