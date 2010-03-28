package designpatterns.bridgepattern.implementator;

public class SonyFunctionsImplementator implements FunctionsImplementator{
    public void doOff() { System.out.println("Sony off"); }
    public void doOn()  { System.out.println("Sony on"); }
    public void doLightOnOff() { System.out.println("Sony Light on/off");}
}
