package designpatterns.bridgepattern.implementator;

public class SonyFunctionsImplementator implements FunctionsImplementator {
    @Override
    public void doOff() {
        System.out.println("Sony off");
    }

    @Override
    public void doOn() {
        System.out.println("Sony on");
    }

    public void doLightOnOff() {
        System.out.println("Sony Light on/off");
    }
}
