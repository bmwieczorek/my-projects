package designpatterns.bridgepattern.implementator;

public class DefaultFunctionsImplementator implements FunctionsImplementator {
    @Override
    public void doOff() {
        System.out.println("Simple off");
    }

    @Override
    public void doOn() {
        System.out.println("Simple on");
    }
}
