package effectivejava.item11.visibility;

public class Subclass extends Superclass {

    // Cannot reduce the visibility of the inherited method from Superclass

    @Override
    public void pub() {
        // can only be public
    }

    @Override
    protected void prot() {
        // can only be protected or public
    }

    @Override
    void pack() {
        // can only be protected, public or default package
    }

}
