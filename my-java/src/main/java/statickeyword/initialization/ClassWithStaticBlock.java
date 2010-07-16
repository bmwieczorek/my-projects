package statickeyword.initialization;

public class ClassWithStaticBlock {
    {
        System.out.println("Static block in " + ClassWithStaticBlock.class.getName());
    }

    public ClassWithStaticBlock() {
        System.out.println("Constructor block in " + ClassWithStaticBlock.class.getName());
    }

}
