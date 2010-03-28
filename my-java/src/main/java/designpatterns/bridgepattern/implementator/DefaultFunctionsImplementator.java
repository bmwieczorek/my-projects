package designpatterns.bridgepattern.implementator;

public class DefaultFunctionsImplementator implements FunctionsImplementator{
    public void doOff() { System.out.println("Simple off");}
    public void doOn() { System.out.println("Simple on");}
}
