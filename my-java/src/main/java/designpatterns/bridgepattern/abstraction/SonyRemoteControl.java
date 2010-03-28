package designpatterns.bridgepattern.abstraction;

import designpatterns.bridgepattern.implementator.FunctionsImplementator;
import designpatterns.bridgepattern.implementator.SonyFunctionsImplementator;

public class SonyRemoteControl extends DefaultRemoteControllAbstraction{
    private SonyFunctionsImplementator sonyFunctionsImplementator = new SonyFunctionsImplementator();
    @Override
    protected FunctionsImplementator getFunctionsImplementator() {
        return sonyFunctionsImplementator;
    }
    public void lightOnOff(){
        sonyFunctionsImplementator.doLightOnOff();
    }
}


